package com.wmedya.payu.client.model;

/**
 * Enumeration for merchant platform selection. It changes the post url of alu
 * used by client.
 * <p>
 * <b>TR</b> - https://secure.payu.com.tr/order/alu/v3<br>
 * <b>RO</b> - https://secure.payu.ro/order/alu/v3<br>
 * <b>RU</b> - https://secure.payu.ru/order/alu/v3<br>
 * <b>UA</b> - https://secure.payu.ua/order/alu/v3<br>
 * <b>HU</b> - https://secure.payu.hu/order/alu/v3
 * 
 * @author Yusuf Ziya Tekin
 *
 */
public enum MerchantPlatform {

	TR, RO, RU, UA, HU

}
