var productId, productName;

function removeCategoryDialog(el) {
	productId = $(el).attr('data-product-id');
	productName = $(el).attr('data-product-name');
	$('.remove-product-modal').find('#product-name').text(productName);
}

function removeProduct() {
	$('.remove-product-modal').modal('hide');
	window.location = "/product/remove/" + productId;
}