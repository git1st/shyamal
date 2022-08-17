$(document).ready(function() {
	
	function getBrokersByBroker(value) {
		$.get('/rest/customer/' + value, function(data) {
			console.log(data);
			if( data ) {
				populateCustomersList( data );
			}
		});
	}
	
	function populateCustomersList( customersList ) {
		$('#customerSel').empty().append('<option selected="selected" value="">-- Select Customer --</option>');
		$.each(customersList, function(k, v) {   
		     $('#customerSel').append($("<option></option>")
		                    .attr("value",v.id).text(v.firstname + ' ' + (v.lastname?' '+v.lastname:'') )); 
		});
	}
	
	$('#brokerSel').on('change', function() {
		var value = $(this).val();
		console.log("brokerID:"+value);
		if($(this).val()<0)
		{
			$('#customerSel').empty().append('<option selected="selected" value="">-- Select Customer --</option>');
		}
		else
		{
			var filteredCustomers = getBrokersByBroker(value);
		}
	});
	
	
	$('#addSalesBtn').on('click', function() {
		var id = $('#productSel').val();
		var name = $("#productSel option:selected").text();
		var quantity = $('#quantity').val();
		var price = $('#price').val();
		
		console.log(id+":"+name+":"+quantity);
		//var tag = $("#productSel option:selected").attr("data-");
		//var authors = $("#productSel option:selected").attr("data-authors");
		
		if( id && !productAlreadyExist(id) && name && quantity) {
			//var product = { id: id, name: name, tag: tag, authors: authors };
			var product = { id: id, name: name, quantity:quantity, price:price};
			productToSale.push(product);
			$('#productSel').val('');
			initProductsInTable();
		}
	});
	
	function productAlreadyExist(id) {
		for(var k=0 ; k<productToSale.length ; k++) {
			if( productToSale[k].id == id ) {
				return true;
			}
		}
		return false;
	}
	
	function getIssuedBookIds() {
		var ids = [];
		for(var k=0 ; k<productToSale.length ; k++) {
			ids.push( productToSale[k].id );
		}
		return ids;
	}
	
	function getQtys() {
		var qtys = [];
		for(var j=0 ; j<productToSale.length ; j++) {
			qtys.push( productToSale[j].quantity );
		}
		return qtys;
	}
	
	function getPrices() {
		var prices = [];
		for(var j=0 ; j<productToSale.length ; j++) {
			prices.push( productToSale[j].price );
		}
		return prices;
	}

	
	$('#saveBtn').on('click', function(){
		console.log(productToSale);
		var errors = validate();
		if( errors.length > 0 ) {
			$('.errors-modal').find('.modal-body').html( errors.join('<br />') );
			$('.errors-modal').modal('show');
		} else {
			var issue = {
					salesdate: $('#salesdate').val(),
					customer: $('#customerSel').val(),
					qtys:getQtys().join(),
					prices:getPrices().join(),
					products: getIssuedBookIds().join()
			}
			console.log("issueList:"+issue);
			$.post( "/rest/sales/save", issue).done(function (data){
				if( data=='success' ) {
					window.location = '/sales/new';
				}
			});
		}
	});
	
	function validate() {
		var errors = []
		var customer = $('#customerSel').val();
		if( !customer ) {
			errors.push('- Select Customer');
		}
		if( productToSale.length == 0 ) {
			errors.push('- Add Product to sale');
		}
		return errors;
	}
	
});

var productToSale = [];

function initProductsInTable() {
	
	var trs = '';
	for( var k=0 ; k<productToSale.length ; k++ ) {
		var rowNum = k+1;
		trs += '<tr>';
		trs += '<td>'+rowNum+'</td>';
		trs += '<td>'+productToSale[k].name+'</td>';
		trs += '<td>'+productToSale[k].quantity+'</td>';
		trs += '<td>'+productToSale[k].price+'</td>';
		trs += '<td>'+(productToSale[k].quantity)*(productToSale[k].price)+'</td>';
	//	trs += '<td>'+productToSale[k].authors+'</td>';
		trs += '<td><a href="javascript:void(0)"  onclick="removeFromTable('+rowNum+', '+productToSale[k].id+')"><i class="fa fa-remove"></i></a></td>';
		trs += '</tr>';
	}
	$("#saleProductTable").find("tr:gt(0)").remove();
	$('#saleProductTable').append( trs );
}

function removeFromTable(rowNum, id) {
	$('#saleProductTable tr:eq('+(rowNum)+')').remove();
	removeFromProductsIssueList(id);
	initProductsInTable();
}




function removeFromProductsIssueList(id) {
	for( var k=0 ; k<productToSale.length ; k++ ) {
		if( productToSale[k].id == id ) {
			productToSale.splice(k, 1);
			break;
		}
	}
}