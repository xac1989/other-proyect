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
 * <p>Java class for QueryLoyaltyMemberResponseEBMType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="QueryLoyaltyMemberResponseEBMType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://xmlns.cencosud.corp/Core/EBM/Common/EBM}ResponseEBMType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="DataArea" type="{http://xmlns.cencosud.corp/Loyalty/Core/EBM/LoyaltyMember}QueryLoyaltyMemberResponseType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QueryLoyaltyMemberResponseEBMType", propOrder = {
    "dataArea"
})
public class QueryLoyaltyMemberResponseEBMType
    extends ResponseEBMType
{

    @XmlElement(name = "DataArea")
    protected QueryLoyaltyMemberResponseType dataArea;

    /**
     * Gets the value of the dataArea property.
     * 
     * @return
     *     possible object is
     *     {@link QueryLoyaltyMemberResponseType }
     *     
     */
    public QueryLoyaltyMemberResponseType getDataArea() {
        return dataArea;
    }

    /**
     * Sets the value of the dataArea property.
     * 
     * @param value
     *     allowed object is
     *     {@link QueryLoyaltyMemberResponseType }
     *     
     */
    public void setDataArea(QueryLoyaltyMemberResponseType value) {
        this.dataArea = value;
    }

}
