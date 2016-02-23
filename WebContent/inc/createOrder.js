$(document).ready(function() {
	$("input[name=newCustomer]:radio").click(function() {
		var divId = jQuery(this).val();
		
		$("div#newCustomer").hide();
		$("div#existingCustomer").hide();
		
		$("div#" + divId).show();
	});
});
