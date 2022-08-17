var stockId,productName,stockQuantity;

function removeStockDialog(el) {
	stockId = $(el).attr('data-stock-id');
	productName = $(el).attr('data-productname');
	stockQuantity = $(el).attr('data-stock-qty');
	$('.remove-stock-modal').find("#stock-id").text(productName+"-"+stockQuantity);
}

function removeStock() {
	$('.remove-stock-modal').modal('hide');
	window.location = "/stock/remove/" + stockId;
}