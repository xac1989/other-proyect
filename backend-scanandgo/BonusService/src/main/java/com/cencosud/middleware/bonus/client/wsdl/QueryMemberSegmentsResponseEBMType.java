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
 * <p>Java class for QueryMemberSegmentsResponseEBMType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="QueryMemberSegmentsResponseEBMType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://xmlns.cencosud.corp/Core/EBM/Common/EBM}ResponseEBMType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="DataArea" type="{http://xmlns.cencosud.corp/Loyalty/Core/EBM/LoyaltyMember}QueryMemberSegmentsResponseType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QueryMemberSegmentsResponseEBMType", propOrder = {
    "dataArea"
})
public class QueryMemberSegmentsResponseEBMType
    extends ResponseEBMType
{

    @XmlElement(name = "DataArea")
    protected QueryMemberSegmentsResponseType dataArea;

    /**
     * Gets the value of the dataArea property.
     * 
     * @return
     *     possible object is
     *     {@link QueryMemberSegmentsResponseType }
     *     
     */
    public QueryMemberSegmentsResponseType getDataArea() {
        return dataArea;
    }

    /**
     * Sets the value of the dataArea property.
     * 
     * @param value
     *     allowed object is
     *     {@link QueryMemberSegmentsResponseType }
     *     
     */
    public void setDataArea(QueryMemberSegmentsResponseType value) {
        this.dataArea = value;
    }

}
