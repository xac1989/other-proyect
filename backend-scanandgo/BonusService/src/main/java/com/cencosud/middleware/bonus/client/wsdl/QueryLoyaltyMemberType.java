//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.05.30 at 04:53:43 PM CDT 
//


package com.cencosud.middleware.bonus.client.wsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for QueryLoyaltyMemberType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="QueryLoyaltyMemberType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="QueryLoyaltyMember" type="{http://xmlns.cencosud.corp/Loyalty/LoyCommonTypes}MemberIdentifierType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QueryLoyaltyMemberType", propOrder = {
    "queryLoyaltyMember"
})
public class QueryLoyaltyMemberType {

    @XmlElement(name = "QueryLoyaltyMember", required = true)
    protected MemberIdentifierType queryLoyaltyMember;

    /**
     * Gets the value of the queryLoyaltyMember property.
     * 
     * @return
     *     possible object is
     *     {@link MemberIdentifierType }
     *     
     */
    public MemberIdentifierType getQueryLoyaltyMember() {
        return queryLoyaltyMember;
    }

    /**
     * Sets the value of the queryLoyaltyMember property.
     * 
     * @param value
     *     allowed object is
     *     {@link MemberIdentifierType }
     *     
     */
    public void setQueryLoyaltyMember(MemberIdentifierType value) {
        this.queryLoyaltyMember = value;
    }

}
