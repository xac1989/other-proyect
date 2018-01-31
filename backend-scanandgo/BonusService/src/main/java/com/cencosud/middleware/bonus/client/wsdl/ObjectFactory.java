//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.05.30 at 04:53:43 PM CDT 
//


package com.cencosud.middleware.bonus.client.wsdl;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.cencosud.middleware.bonus.client.wsdl package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _QueryMemberSegmentsRequestEBM_QNAME = new QName("http://xmlns.cencosud.corp/Loyalty/Core/EBM/LoyaltyMember", "QueryMemberSegmentsRequestEBM");
    private final static QName _QueryMemberSegmentsResponseEBM_QNAME = new QName("http://xmlns.cencosud.corp/Loyalty/Core/EBM/LoyaltyMember", "QueryMemberSegmentsResponseEBM");
    private final static QName _QueryLoyaltyMemberRequestEBM_QNAME = new QName("http://xmlns.cencosud.corp/Loyalty/Core/EBM/LoyaltyMember", "QueryLoyaltyMemberRequestEBM");
    private final static QName _QueryLoyaltyMemberFullRequestEBM_QNAME = new QName("http://xmlns.cencosud.corp/Loyalty/Core/EBM/LoyaltyMember", "QueryLoyaltyMemberFullRequestEBM");
    private final static QName _QueryLoyaltyMemberResponseEBM_QNAME = new QName("http://xmlns.cencosud.corp/Loyalty/Core/EBM/LoyaltyMember", "QueryLoyaltyMemberResponseEBM");
    private final static QName _QueryLoyaltyMemberFullResponseEBM_QNAME = new QName("http://xmlns.cencosud.corp/Loyalty/Core/EBM/LoyaltyMember", "QueryLoyaltyMemberFullResponseEBM");
    private final static QName _EnrolLoyaltyMemberRequestEBM_QNAME = new QName("http://xmlns.cencosud.corp/Loyalty/Core/EBM/LoyaltyMember", "EnrolLoyaltyMemberRequestEBM");
    private final static QName _EnrolLoyaltyMemberResponseEBM_QNAME = new QName("http://xmlns.cencosud.corp/Loyalty/Core/EBM/LoyaltyMember", "EnrolLoyaltyMemberResponseEBM");
    private final static QName _UpdateLoyaltyMemberRequestEBM_QNAME = new QName("http://xmlns.cencosud.corp/Loyalty/Core/EBM/LoyaltyMember", "UpdateLoyaltyMemberRequestEBM");
    private final static QName _UpdateLoyaltyMemberResponseEBM_QNAME = new QName("http://xmlns.cencosud.corp/Loyalty/Core/EBM/LoyaltyMember", "UpdateLoyaltyMemberResponseEBM");
    private final static QName _QueryLoyaltyMemberIdByDNIRequestEBM_QNAME = new QName("http://xmlns.cencosud.corp/Loyalty/Core/EBM/LoyaltyMember", "QueryLoyaltyMemberIdByDNIRequestEBM");
    private final static QName _QueryLoyaltyMemberIdByDNIResponseEBM_QNAME = new QName("http://xmlns.cencosud.corp/Loyalty/Core/EBM/LoyaltyMember", "QueryLoyaltyMemberIdByDNIResponseEBM");
    private final static QName _Error_QNAME = new QName("http://xmlns.cencosud.corp/Loyalty/LoyCommonTypes", "Error");
    private final static QName _EBMHeader_QNAME = new QName("http://xmlns.cencosud.corp/Core/EBM/Common/EBM", "EBMHeader");
    private final static QName _ErrorDetail_QNAME = new QName("http://xmlns.cencosud.corp/Core/EBM/Common/EBM", "ErrorDetail");
    private final static QName _EBMAddressingTypeReplyToAddress_QNAME = new QName("http://xmlns.cencosud.corp/Core/EBM/Common/EBM", "ReplyToAddress");
    private final static QName _EBMAddressingTypeCorrelID_QNAME = new QName("http://xmlns.cencosud.corp/Core/EBM/Common/EBM", "CorrelID");
    private final static QName _EBMTrackingTypeParentEBMID_QNAME = new QName("http://xmlns.cencosud.corp/Core/EBM/Common/EBM", "ParentEBMID");
    private final static QName _EBMTrackingTypeIntegrationCode_QNAME = new QName("http://xmlns.cencosud.corp/Core/EBM/Common/EBM", "IntegrationCode");
    private final static QName _EBMTrackingTypeReferenceID_QNAME = new QName("http://xmlns.cencosud.corp/Core/EBM/Common/EBM", "ReferenceID");
    private final static QName _ResponseEBMTypeErrorCode_QNAME = new QName("http://xmlns.cencosud.corp/Core/EBM/Common/EBM", "ErrorCode");
    private final static QName _SenderTypeLegalEntity_QNAME = new QName("http://xmlns.cencosud.corp/Core/EBM/Common/EBM", "LegalEntity");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.cencosud.middleware.bonus.client.wsdl
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link QueryMemberSegmentsResponseType }
     * 
     */
    public QueryMemberSegmentsResponseType createQueryMemberSegmentsResponseType() {
        return new QueryMemberSegmentsResponseType();
    }

    /**
     * Create an instance of {@link QueryMemberSegmentsRequestEBMType }
     * 
     */
    public QueryMemberSegmentsRequestEBMType createQueryMemberSegmentsRequestEBMType() {
        return new QueryMemberSegmentsRequestEBMType();
    }

    /**
     * Create an instance of {@link QueryMemberSegmentsResponseEBMType }
     * 
     */
    public QueryMemberSegmentsResponseEBMType createQueryMemberSegmentsResponseEBMType() {
        return new QueryMemberSegmentsResponseEBMType();
    }

    /**
     * Create an instance of {@link QueryLoyaltyMemberRequestEBMType }
     * 
     */
    public QueryLoyaltyMemberRequestEBMType createQueryLoyaltyMemberRequestEBMType() {
        return new QueryLoyaltyMemberRequestEBMType();
    }

    /**
     * Create an instance of {@link QueryLoyaltyMemberFullRequestEBMType }
     * 
     */
    public QueryLoyaltyMemberFullRequestEBMType createQueryLoyaltyMemberFullRequestEBMType() {
        return new QueryLoyaltyMemberFullRequestEBMType();
    }

    /**
     * Create an instance of {@link QueryLoyaltyMemberResponseEBMType }
     * 
     */
    public QueryLoyaltyMemberResponseEBMType createQueryLoyaltyMemberResponseEBMType() {
        return new QueryLoyaltyMemberResponseEBMType();
    }

    /**
     * Create an instance of {@link QueryLoyaltyMemberFullResponseEBMType }
     * 
     */
    public QueryLoyaltyMemberFullResponseEBMType createQueryLoyaltyMemberFullResponseEBMType() {
        return new QueryLoyaltyMemberFullResponseEBMType();
    }

    /**
     * Create an instance of {@link EnrolLoyaltyMemberRequestEBMType }
     * 
     */
    public EnrolLoyaltyMemberRequestEBMType createEnrolLoyaltyMemberRequestEBMType() {
        return new EnrolLoyaltyMemberRequestEBMType();
    }

    /**
     * Create an instance of {@link EnrolLoyaltyMemberResponseEBMType }
     * 
     */
    public EnrolLoyaltyMemberResponseEBMType createEnrolLoyaltyMemberResponseEBMType() {
        return new EnrolLoyaltyMemberResponseEBMType();
    }

    /**
     * Create an instance of {@link UpdateLoyaltyMemberRequestEBMType }
     * 
     */
    public UpdateLoyaltyMemberRequestEBMType createUpdateLoyaltyMemberRequestEBMType() {
        return new UpdateLoyaltyMemberRequestEBMType();
    }

    /**
     * Create an instance of {@link UpdateLoyaltyMemberResponseEBMType }
     * 
     */
    public UpdateLoyaltyMemberResponseEBMType createUpdateLoyaltyMemberResponseEBMType() {
        return new UpdateLoyaltyMemberResponseEBMType();
    }

    /**
     * Create an instance of {@link QueryLoyaltyMemberIdByDNIRequestEBMType }
     * 
     */
    public QueryLoyaltyMemberIdByDNIRequestEBMType createQueryLoyaltyMemberIdByDNIRequestEBMType() {
        return new QueryLoyaltyMemberIdByDNIRequestEBMType();
    }

    /**
     * Create an instance of {@link QueryLoyaltyMemberIdByDNIResponseEBMType }
     * 
     */
    public QueryLoyaltyMemberIdByDNIResponseEBMType createQueryLoyaltyMemberIdByDNIResponseEBMType() {
        return new QueryLoyaltyMemberIdByDNIResponseEBMType();
    }

    /**
     * Create an instance of {@link QueryMemberSegmentsType }
     * 
     */
    public QueryMemberSegmentsType createQueryMemberSegmentsType() {
        return new QueryMemberSegmentsType();
    }

    /**
     * Create an instance of {@link QueryLoyaltyMemberType }
     * 
     */
    public QueryLoyaltyMemberType createQueryLoyaltyMemberType() {
        return new QueryLoyaltyMemberType();
    }

    /**
     * Create an instance of {@link QueryLoyaltyMemberResponseType }
     * 
     */
    public QueryLoyaltyMemberResponseType createQueryLoyaltyMemberResponseType() {
        return new QueryLoyaltyMemberResponseType();
    }

    /**
     * Create an instance of {@link QueryLoyaltyMemberFullResponseType }
     * 
     */
    public QueryLoyaltyMemberFullResponseType createQueryLoyaltyMemberFullResponseType() {
        return new QueryLoyaltyMemberFullResponseType();
    }

    /**
     * Create an instance of {@link EnrolLoyaltyMemberType }
     * 
     */
    public EnrolLoyaltyMemberType createEnrolLoyaltyMemberType() {
        return new EnrolLoyaltyMemberType();
    }

    /**
     * Create an instance of {@link EnrolLoyaltyMemberResponseType }
     * 
     */
    public EnrolLoyaltyMemberResponseType createEnrolLoyaltyMemberResponseType() {
        return new EnrolLoyaltyMemberResponseType();
    }

    /**
     * Create an instance of {@link UpdateLoyaltyMemberType }
     * 
     */
    public UpdateLoyaltyMemberType createUpdateLoyaltyMemberType() {
        return new UpdateLoyaltyMemberType();
    }

    /**
     * Create an instance of {@link UpdateLoyaltyMemberResponseType }
     * 
     */
    public UpdateLoyaltyMemberResponseType createUpdateLoyaltyMemberResponseType() {
        return new UpdateLoyaltyMemberResponseType();
    }

    /**
     * Create an instance of {@link QueryLoyaltyMemberIdByDNIType }
     * 
     */
    public QueryLoyaltyMemberIdByDNIType createQueryLoyaltyMemberIdByDNIType() {
        return new QueryLoyaltyMemberIdByDNIType();
    }

    /**
     * Create an instance of {@link QueryLoyaltyMemberIdByDNIResponseType }
     * 
     */
    public QueryLoyaltyMemberIdByDNIResponseType createQueryLoyaltyMemberIdByDNIResponseType() {
        return new QueryLoyaltyMemberIdByDNIResponseType();
    }

    /**
     * Create an instance of {@link AnswerType }
     * 
     */
    public AnswerType createAnswerType() {
        return new AnswerType();
    }

    /**
     * Create an instance of {@link ListOfAnswerType }
     * 
     */
    public ListOfAnswerType createListOfAnswerType() {
        return new ListOfAnswerType();
    }

    /**
     * Create an instance of {@link StringValidType }
     * 
     */
    public StringValidType createStringValidType() {
        return new StringValidType();
    }

    /**
     * Create an instance of {@link MemberType }
     * 
     */
    public MemberType createMemberType() {
        return new MemberType();
    }

    /**
     * Create an instance of {@link MemberPreferencesType }
     * 
     */
    public MemberPreferencesType createMemberPreferencesType() {
        return new MemberPreferencesType();
    }

    /**
     * Create an instance of {@link MemberSurveyType }
     * 
     */
    public MemberSurveyType createMemberSurveyType() {
        return new MemberSurveyType();
    }

    /**
     * Create an instance of {@link LoyaltyMemberFullType }
     * 
     */
    public LoyaltyMemberFullType createLoyaltyMemberFullType() {
        return new LoyaltyMemberFullType();
    }

    /**
     * Create an instance of {@link LoyaltyMemberType }
     * 
     */
    public LoyaltyMemberType createLoyaltyMemberType() {
        return new LoyaltyMemberType();
    }

    /**
     * Create an instance of {@link ErrorType }
     * 
     */
    public ErrorType createErrorType() {
        return new ErrorType();
    }

    /**
     * Create an instance of {@link MemberIdentifierType }
     * 
     */
    public MemberIdentifierType createMemberIdentifierType() {
        return new MemberIdentifierType();
    }

    /**
     * Create an instance of {@link AddressType }
     * 
     */
    public AddressType createAddressType() {
        return new AddressType();
    }

    /**
     * Create an instance of {@link QuoteItemType }
     * 
     */
    public QuoteItemType createQuoteItemType() {
        return new QuoteItemType();
    }

    /**
     * Create an instance of {@link ListOfQuoteItemsType }
     * 
     */
    public ListOfQuoteItemsType createListOfQuoteItemsType() {
        return new ListOfQuoteItemsType();
    }

    /**
     * Create an instance of {@link VoucherType }
     * 
     */
    public VoucherType createVoucherType() {
        return new VoucherType();
    }

    /**
     * Create an instance of {@link ListOfVoucherItemsType }
     * 
     */
    public ListOfVoucherItemsType createListOfVoucherItemsType() {
        return new ListOfVoucherItemsType();
    }

    /**
     * Create an instance of {@link EBMHeaderType }
     * 
     */
    public EBMHeaderType createEBMHeaderType() {
        return new EBMHeaderType();
    }

    /**
     * Create an instance of {@link ErrorDetailType }
     * 
     */
    public ErrorDetailType createErrorDetailType() {
        return new ErrorDetailType();
    }

    /**
     * Create an instance of {@link SenderType }
     * 
     */
    public SenderType createSenderType() {
        return new SenderType();
    }

    /**
     * Create an instance of {@link EBMType }
     * 
     */
    public EBMType createEBMType() {
        return new EBMType();
    }

    /**
     * Create an instance of {@link ResponseEBMType }
     * 
     */
    public ResponseEBMType createResponseEBMType() {
        return new ResponseEBMType();
    }

    /**
     * Create an instance of {@link EBMTrackingType }
     * 
     */
    public EBMTrackingType createEBMTrackingType() {
        return new EBMTrackingType();
    }

    /**
     * Create an instance of {@link EBMAddressingType }
     * 
     */
    public EBMAddressingType createEBMAddressingType() {
        return new EBMAddressingType();
    }

    /**
     * Create an instance of {@link QueryMemberSegmentsResponseType.MemberSegments }
     * 
     */
    public QueryMemberSegmentsResponseType.MemberSegments createQueryMemberSegmentsResponseTypeMemberSegments() {
        return new QueryMemberSegmentsResponseType.MemberSegments();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryMemberSegmentsRequestEBMType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xmlns.cencosud.corp/Loyalty/Core/EBM/LoyaltyMember", name = "QueryMemberSegmentsRequestEBM")
    public JAXBElement<QueryMemberSegmentsRequestEBMType> createQueryMemberSegmentsRequestEBM(QueryMemberSegmentsRequestEBMType value) {
        return new JAXBElement<QueryMemberSegmentsRequestEBMType>(_QueryMemberSegmentsRequestEBM_QNAME, QueryMemberSegmentsRequestEBMType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryMemberSegmentsResponseEBMType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xmlns.cencosud.corp/Loyalty/Core/EBM/LoyaltyMember", name = "QueryMemberSegmentsResponseEBM")
    public JAXBElement<QueryMemberSegmentsResponseEBMType> createQueryMemberSegmentsResponseEBM(QueryMemberSegmentsResponseEBMType value) {
        return new JAXBElement<QueryMemberSegmentsResponseEBMType>(_QueryMemberSegmentsResponseEBM_QNAME, QueryMemberSegmentsResponseEBMType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryLoyaltyMemberRequestEBMType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xmlns.cencosud.corp/Loyalty/Core/EBM/LoyaltyMember", name = "QueryLoyaltyMemberRequestEBM")
    public JAXBElement<QueryLoyaltyMemberRequestEBMType> createQueryLoyaltyMemberRequestEBM(QueryLoyaltyMemberRequestEBMType value) {
        return new JAXBElement<QueryLoyaltyMemberRequestEBMType>(_QueryLoyaltyMemberRequestEBM_QNAME, QueryLoyaltyMemberRequestEBMType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryLoyaltyMemberFullRequestEBMType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xmlns.cencosud.corp/Loyalty/Core/EBM/LoyaltyMember", name = "QueryLoyaltyMemberFullRequestEBM")
    public JAXBElement<QueryLoyaltyMemberFullRequestEBMType> createQueryLoyaltyMemberFullRequestEBM(QueryLoyaltyMemberFullRequestEBMType value) {
        return new JAXBElement<QueryLoyaltyMemberFullRequestEBMType>(_QueryLoyaltyMemberFullRequestEBM_QNAME, QueryLoyaltyMemberFullRequestEBMType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryLoyaltyMemberResponseEBMType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xmlns.cencosud.corp/Loyalty/Core/EBM/LoyaltyMember", name = "QueryLoyaltyMemberResponseEBM")
    public JAXBElement<QueryLoyaltyMemberResponseEBMType> createQueryLoyaltyMemberResponseEBM(QueryLoyaltyMemberResponseEBMType value) {
        return new JAXBElement<QueryLoyaltyMemberResponseEBMType>(_QueryLoyaltyMemberResponseEBM_QNAME, QueryLoyaltyMemberResponseEBMType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryLoyaltyMemberFullResponseEBMType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xmlns.cencosud.corp/Loyalty/Core/EBM/LoyaltyMember", name = "QueryLoyaltyMemberFullResponseEBM")
    public JAXBElement<QueryLoyaltyMemberFullResponseEBMType> createQueryLoyaltyMemberFullResponseEBM(QueryLoyaltyMemberFullResponseEBMType value) {
        return new JAXBElement<QueryLoyaltyMemberFullResponseEBMType>(_QueryLoyaltyMemberFullResponseEBM_QNAME, QueryLoyaltyMemberFullResponseEBMType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EnrolLoyaltyMemberRequestEBMType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xmlns.cencosud.corp/Loyalty/Core/EBM/LoyaltyMember", name = "EnrolLoyaltyMemberRequestEBM")
    public JAXBElement<EnrolLoyaltyMemberRequestEBMType> createEnrolLoyaltyMemberRequestEBM(EnrolLoyaltyMemberRequestEBMType value) {
        return new JAXBElement<EnrolLoyaltyMemberRequestEBMType>(_EnrolLoyaltyMemberRequestEBM_QNAME, EnrolLoyaltyMemberRequestEBMType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EnrolLoyaltyMemberResponseEBMType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xmlns.cencosud.corp/Loyalty/Core/EBM/LoyaltyMember", name = "EnrolLoyaltyMemberResponseEBM")
    public JAXBElement<EnrolLoyaltyMemberResponseEBMType> createEnrolLoyaltyMemberResponseEBM(EnrolLoyaltyMemberResponseEBMType value) {
        return new JAXBElement<EnrolLoyaltyMemberResponseEBMType>(_EnrolLoyaltyMemberResponseEBM_QNAME, EnrolLoyaltyMemberResponseEBMType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateLoyaltyMemberRequestEBMType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xmlns.cencosud.corp/Loyalty/Core/EBM/LoyaltyMember", name = "UpdateLoyaltyMemberRequestEBM")
    public JAXBElement<UpdateLoyaltyMemberRequestEBMType> createUpdateLoyaltyMemberRequestEBM(UpdateLoyaltyMemberRequestEBMType value) {
        return new JAXBElement<UpdateLoyaltyMemberRequestEBMType>(_UpdateLoyaltyMemberRequestEBM_QNAME, UpdateLoyaltyMemberRequestEBMType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateLoyaltyMemberResponseEBMType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xmlns.cencosud.corp/Loyalty/Core/EBM/LoyaltyMember", name = "UpdateLoyaltyMemberResponseEBM")
    public JAXBElement<UpdateLoyaltyMemberResponseEBMType> createUpdateLoyaltyMemberResponseEBM(UpdateLoyaltyMemberResponseEBMType value) {
        return new JAXBElement<UpdateLoyaltyMemberResponseEBMType>(_UpdateLoyaltyMemberResponseEBM_QNAME, UpdateLoyaltyMemberResponseEBMType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryLoyaltyMemberIdByDNIRequestEBMType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xmlns.cencosud.corp/Loyalty/Core/EBM/LoyaltyMember", name = "QueryLoyaltyMemberIdByDNIRequestEBM")
    public JAXBElement<QueryLoyaltyMemberIdByDNIRequestEBMType> createQueryLoyaltyMemberIdByDNIRequestEBM(QueryLoyaltyMemberIdByDNIRequestEBMType value) {
        return new JAXBElement<QueryLoyaltyMemberIdByDNIRequestEBMType>(_QueryLoyaltyMemberIdByDNIRequestEBM_QNAME, QueryLoyaltyMemberIdByDNIRequestEBMType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryLoyaltyMemberIdByDNIResponseEBMType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xmlns.cencosud.corp/Loyalty/Core/EBM/LoyaltyMember", name = "QueryLoyaltyMemberIdByDNIResponseEBM")
    public JAXBElement<QueryLoyaltyMemberIdByDNIResponseEBMType> createQueryLoyaltyMemberIdByDNIResponseEBM(QueryLoyaltyMemberIdByDNIResponseEBMType value) {
        return new JAXBElement<QueryLoyaltyMemberIdByDNIResponseEBMType>(_QueryLoyaltyMemberIdByDNIResponseEBM_QNAME, QueryLoyaltyMemberIdByDNIResponseEBMType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ErrorType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xmlns.cencosud.corp/Loyalty/LoyCommonTypes", name = "Error")
    public JAXBElement<ErrorType> createError(ErrorType value) {
        return new JAXBElement<ErrorType>(_Error_QNAME, ErrorType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EBMHeaderType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xmlns.cencosud.corp/Core/EBM/Common/EBM", name = "EBMHeader")
    public JAXBElement<EBMHeaderType> createEBMHeader(EBMHeaderType value) {
        return new JAXBElement<EBMHeaderType>(_EBMHeader_QNAME, EBMHeaderType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ErrorDetailType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xmlns.cencosud.corp/Core/EBM/Common/EBM", name = "ErrorDetail")
    public JAXBElement<ErrorDetailType> createErrorDetail(ErrorDetailType value) {
        return new JAXBElement<ErrorDetailType>(_ErrorDetail_QNAME, ErrorDetailType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xmlns.cencosud.corp/Core/EBM/Common/EBM", name = "ReplyToAddress", scope = EBMAddressingType.class)
    public JAXBElement<String> createEBMAddressingTypeReplyToAddress(String value) {
        return new JAXBElement<String>(_EBMAddressingTypeReplyToAddress_QNAME, String.class, EBMAddressingType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xmlns.cencosud.corp/Core/EBM/Common/EBM", name = "CorrelID", scope = EBMAddressingType.class)
    public JAXBElement<String> createEBMAddressingTypeCorrelID(String value) {
        return new JAXBElement<String>(_EBMAddressingTypeCorrelID_QNAME, String.class, EBMAddressingType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xmlns.cencosud.corp/Core/EBM/Common/EBM", name = "ParentEBMID", scope = EBMTrackingType.class)
    public JAXBElement<String> createEBMTrackingTypeParentEBMID(String value) {
        return new JAXBElement<String>(_EBMTrackingTypeParentEBMID_QNAME, String.class, EBMTrackingType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xmlns.cencosud.corp/Core/EBM/Common/EBM", name = "IntegrationCode", scope = EBMTrackingType.class)
    public JAXBElement<String> createEBMTrackingTypeIntegrationCode(String value) {
        return new JAXBElement<String>(_EBMTrackingTypeIntegrationCode_QNAME, String.class, EBMTrackingType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xmlns.cencosud.corp/Core/EBM/Common/EBM", name = "ReferenceID", scope = EBMTrackingType.class)
    public JAXBElement<String> createEBMTrackingTypeReferenceID(String value) {
        return new JAXBElement<String>(_EBMTrackingTypeReferenceID_QNAME, String.class, EBMTrackingType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xmlns.cencosud.corp/Core/EBM/Common/EBM", name = "ErrorCode", scope = ResponseEBMType.class)
    public JAXBElement<String> createResponseEBMTypeErrorCode(String value) {
        return new JAXBElement<String>(_ResponseEBMTypeErrorCode_QNAME, String.class, ResponseEBMType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xmlns.cencosud.corp/Core/EBM/Common/EBM", name = "LegalEntity", scope = SenderType.class)
    public JAXBElement<String> createSenderTypeLegalEntity(String value) {
        return new JAXBElement<String>(_SenderTypeLegalEntity_QNAME, String.class, SenderType.class, value);
    }

}
