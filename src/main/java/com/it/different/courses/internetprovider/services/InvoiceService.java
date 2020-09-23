package com.it.different.courses.internetprovider.services;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.stream.Collectors;

import com.it.different.courses.internetprovider.persistence.entity.Contract;
import com.it.different.courses.internetprovider.persistence.entity.Contract.ContractStatus;
import com.it.different.courses.internetprovider.persistence.entity.Invoice;
import com.it.different.courses.internetprovider.persistence.entity.Invoice.InvoiceStatus;
import com.it.different.courses.internetprovider.persistence.repository.ContractRepository;
import com.it.different.courses.internetprovider.persistence.repository.InvoiceRepository;
import com.it.different.courses.internetprovider.services.dto.InvoiceDTO;
import com.it.different.courses.internetprovider.services.exceptions.ResourceNotFound;
import com.it.different.courses.internetprovider.services.exceptions.UnauthorizedException;
import lombok.RequiredArgsConstructor;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class InvoiceService {
	private final ContractRepository contractRepository;
	private final InvoiceRepository invoiceRepository;
	private final AuthenticationFacade authenticationFacade;


	@Scheduled(cron = "${invoice.creatingdate}")
	public void createInvoiceForActiveContracts(){
		contractRepository.findAllByStatus(ContractStatus.ACTIVE).forEach(contract -> {
					Invoice invoice = new Invoice(contract.getProduct().getFee(), contract);
					invoiceRepository.save(invoice);
		});
	}

	@Scheduled(cron = "${invoice.susspendcontracts}")
	public void susspendAccound(){
		invoiceRepository.findAllByStatus(InvoiceStatus.CREATED)
				.forEach(invoice -> {
					Contract contract = invoice.getContract();
					contract.suspend();
					contractRepository.save(contract);
					invoice.unpaid();
					invoiceRepository.save(invoice);
				});
	}

	public List<InvoiceDTO> getAll() {
		return invoiceRepository.findAll()
				.stream()
				.map(invoice -> InvoiceDTO.builder()
					.amount(invoice.getAmount())
					.contractId(invoice.getContract().getId())
					.productId(invoice.getContract().getProduct().getId())
					.build())
				.collect(Collectors.toList());
	}

	public void pay(Long invoiceId) {
		Invoice invoice = invoiceRepository.findById(invoiceId).orElseThrow(ResourceNotFound::new);
		if(!authenticationFacade.getAuthentication().getId().equals(invoice.getContract().getCustomer().getId())){
			throw new UnauthorizedException();
		}
		invoice.paid();
		invoiceRepository.save(invoice);
	}
}
