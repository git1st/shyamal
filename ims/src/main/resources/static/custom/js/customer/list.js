var customerId, customerName;

function removeCustomerDialog(el) {
	customerId = $(el).attr('data-customer-id');
	customerName = $(el).attr('data-customer-name');
	$('.remove-customer-modal').find('#customer-name').text(customerName);
}

function removeCustomer() {
	$('.remove-customer-modal').modal('hide');
	window.location = "/customer/remove/" + customerId;
}