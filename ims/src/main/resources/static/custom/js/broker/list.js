var brokerId, brokerName;

function removeCategoryDialog(el) {
	brokerId = $(el).attr('data-broker-id');
	brokerName = $(el).attr('data-broker-name');
	$('.remove-broker-modal').find('#broker-name').text(brokerName);
}

function removeBroker() {
	$('.remove-broker-modal').modal('hide');
	window.location = "/broker/remove/" + brokerId;
}