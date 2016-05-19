package com.sdzee.servlets.testutil;

public class AvoidDuplication {
	
	public static String[] customerFields = new String[] {
			"customerLastName",
			"customerFirstName",
			"customerAddress",
			"customerPhoneNumber",
			"customerEmailAddress",
			"customerPictureFile"
	};
	
	public static String[] orderFields = new String[] {
			"customerLastName",
			"customerFirstName",
			"customerAddress",
			"customerPhoneNumber",
			"customerEmailAddress",
			"customerPictureFile",
			"orderAmount",
			"orderPaymentMethod",
			"orderPaymentStatus",
			"orderShippingMode",
			"orderDeliveryStatus"
	};

}
