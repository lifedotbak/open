//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation,
// v2.2.5-2 generiert
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// �nderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren.
// Generiert: 2014.02.04 um 12:22:03 PM CET
//

package org.xmlsoap.schemas.soap.envelope;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java element interface
 * generated in the org.xmlsoap.schemas.soap.envelope package.
 *
 * <p>An ObjectFactory allows you to programatically construct new instances of the Java
 * representation for XML content. The Java representation of XML content can consist of schema
 * derived interfaces and classes representing the binding of schema type definitions, element
 * declarations and model groups. Factory methods for each of these are provided in this class.
 */
@XmlRegistry
public class ObjectFactory {

    private static final QName _Body_QNAME =
            new QName("http://schemas.xmlsoap.org/soap/envelope/", "Body");
    private static final QName _Envelope_QNAME =
            new QName("http://schemas.xmlsoap.org/soap/envelope/", "Envelope");
    private static final QName _Fault_QNAME =
            new QName("http://schemas.xmlsoap.org/soap/envelope/", "Fault");
    private static final QName _Header_QNAME =
            new QName("http://schemas.xmlsoap.org/soap/envelope/", "Header");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes
     * for package: org.xmlsoap.schemas.soap.envelope
     */
    public ObjectFactory() {}

    /** Create an instance of {@link Body } */
    public Body createBody() {
        return new Body();
    }

    /** Create an instance of {@link Header } */
    public Header createHeader() {
        return new Header();
    }

    /** Create an instance of {@link Envelope } */
    public Envelope createEnvelope() {
        return new Envelope();
    }

    /** Create an instance of {@link Fault } */
    public Fault createFault() {
        return new Fault();
    }

    /** Create an instance of {@link Detail } */
    public Detail createDetail() {
        return new Detail();
    }

    /** Create an instance of {@link JAXBElement }{@code <}{@link Body }{@code >} */
    @XmlElementDecl(namespace = "http://schemas.xmlsoap.org/soap/envelope/", name = "Body")
    public JAXBElement<Body> createBody(Body value) {
        return new JAXBElement<Body>(_Body_QNAME, Body.class, null, value);
    }

    /** Create an instance of {@link JAXBElement }{@code <}{@link Envelope }{@code >} */
    @XmlElementDecl(namespace = "http://schemas.xmlsoap.org/soap/envelope/", name = "Envelope")
    public JAXBElement<Envelope> createEnvelope(Envelope value) {
        return new JAXBElement<Envelope>(_Envelope_QNAME, Envelope.class, null, value);
    }

    /** Create an instance of {@link JAXBElement }{@code <}{@link Fault }{@code >} */
    @XmlElementDecl(namespace = "http://schemas.xmlsoap.org/soap/envelope/", name = "Fault")
    public JAXBElement<Fault> createFault(Fault value) {
        return new JAXBElement<Fault>(_Fault_QNAME, Fault.class, null, value);
    }

    /** Create an instance of {@link JAXBElement }{@code <}{@link Header }{@code >} */
    @XmlElementDecl(namespace = "http://schemas.xmlsoap.org/soap/envelope/", name = "Header")
    public JAXBElement<Header> createHeader(Header value) {
        return new JAXBElement<Header>(_Header_QNAME, Header.class, null, value);
    }
}
