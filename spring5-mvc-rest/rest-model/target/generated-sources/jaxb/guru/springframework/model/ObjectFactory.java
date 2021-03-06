//
// Este arquivo foi gerado pela Arquitetura JavaTM para Implementação de Referência (JAXB) de Bind XML, v2.3.2 
// Consulte <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas as modificações neste arquivo serão perdidas após a recompilação do esquema de origem. 
// Gerado em: 2021.09.03 às 06:49:03 PM BRT 
//


package guru.springframework.model;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the guru.springframework.model package. 
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

    private final static QName _CustomerListDTO_QNAME = new QName("", "CustomerListDTO");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: guru.springframework.model
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CustomerListDTO }
     * 
     */
    public CustomerListDTO createCustomerListDTO() {
        return new CustomerListDTO();
    }

    /**
     * Create an instance of {@link CustomerDTO }
     * 
     */
    public CustomerDTO createCustomerDTO() {
        return new CustomerDTO();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CustomerListDTO }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CustomerListDTO }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "CustomerListDTO")
    public JAXBElement<CustomerListDTO> createCustomerListDTO(CustomerListDTO value) {
        return new JAXBElement<CustomerListDTO>(_CustomerListDTO_QNAME, CustomerListDTO.class, null, value);
    }

}
