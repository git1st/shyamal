$(document).ready(function() {
	$('#gotoListBtn').on('click', function() {
		window.location = "/customer/list";
	});
	
	$('#category-selectbox').on('change', function() {
		updateTagField();
	});
	
	$('#resetBtn').on('click', function() {
		setTimeout(function(){
			updateTagField();
		}, 10);
		
	});
	
	function updateTagField() {
		var shortName =  $("#broker-selectbox option:selected").attr("short-name");
		if( shortName ) {
			$('#tag').val( shortName + '-' );
		}
	}
	
	if( !$('#id').val() ) {
		updateTagField();
	}
});