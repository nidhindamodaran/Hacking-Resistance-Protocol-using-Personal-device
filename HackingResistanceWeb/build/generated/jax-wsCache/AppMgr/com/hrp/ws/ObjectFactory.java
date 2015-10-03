
package com.hrp.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.hrp.ws package. 
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

    private final static QName _GenerateKey_QNAME = new QName("http://ws.hrp.com/", "generateKey");
    private final static QName _GetKeyForUserResponse_QNAME = new QName("http://ws.hrp.com/", "getKeyForUserResponse");
    private final static QName _GenerateKeyResponse_QNAME = new QName("http://ws.hrp.com/", "generateKeyResponse");
    private final static QName _InvalidateKey_QNAME = new QName("http://ws.hrp.com/", "invalidateKey");
    private final static QName _OneTimeLoginResponse_QNAME = new QName("http://ws.hrp.com/", "oneTimeLoginResponse");
    private final static QName _ConfigureApp_QNAME = new QName("http://ws.hrp.com/", "configureApp");
    private final static QName _ConfigureAppResponse_QNAME = new QName("http://ws.hrp.com/", "configureAppResponse");
    private final static QName _InvalidateKeyResponse_QNAME = new QName("http://ws.hrp.com/", "invalidateKeyResponse");
    private final static QName _GetKeyForUser_QNAME = new QName("http://ws.hrp.com/", "getKeyForUser");
    private final static QName _OneTimeLogin_QNAME = new QName("http://ws.hrp.com/", "oneTimeLogin");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.hrp.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link OneTimeLogin }
     * 
     */
    public OneTimeLogin createOneTimeLogin() {
        return new OneTimeLogin();
    }

    /**
     * Create an instance of {@link GetKeyForUserResponse }
     * 
     */
    public GetKeyForUserResponse createGetKeyForUserResponse() {
        return new GetKeyForUserResponse();
    }

    /**
     * Create an instance of {@link InvalidateKey }
     * 
     */
    public InvalidateKey createInvalidateKey() {
        return new InvalidateKey();
    }

    /**
     * Create an instance of {@link GenerateKey }
     * 
     */
    public GenerateKey createGenerateKey() {
        return new GenerateKey();
    }

    /**
     * Create an instance of {@link ConfigureApp }
     * 
     */
    public ConfigureApp createConfigureApp() {
        return new ConfigureApp();
    }

    /**
     * Create an instance of {@link GenerateKeyResponse }
     * 
     */
    public GenerateKeyResponse createGenerateKeyResponse() {
        return new GenerateKeyResponse();
    }

    /**
     * Create an instance of {@link OneTimeLoginResponse }
     * 
     */
    public OneTimeLoginResponse createOneTimeLoginResponse() {
        return new OneTimeLoginResponse();
    }

    /**
     * Create an instance of {@link InvalidateKeyResponse }
     * 
     */
    public InvalidateKeyResponse createInvalidateKeyResponse() {
        return new InvalidateKeyResponse();
    }

    /**
     * Create an instance of {@link GetKeyForUser }
     * 
     */
    public GetKeyForUser createGetKeyForUser() {
        return new GetKeyForUser();
    }

    /**
     * Create an instance of {@link ConfigureAppResponse }
     * 
     */
    public ConfigureAppResponse createConfigureAppResponse() {
        return new ConfigureAppResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GenerateKey }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.hrp.com/", name = "generateKey")
    public JAXBElement<GenerateKey> createGenerateKey(GenerateKey value) {
        return new JAXBElement<GenerateKey>(_GenerateKey_QNAME, GenerateKey.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetKeyForUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.hrp.com/", name = "getKeyForUserResponse")
    public JAXBElement<GetKeyForUserResponse> createGetKeyForUserResponse(GetKeyForUserResponse value) {
        return new JAXBElement<GetKeyForUserResponse>(_GetKeyForUserResponse_QNAME, GetKeyForUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GenerateKeyResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.hrp.com/", name = "generateKeyResponse")
    public JAXBElement<GenerateKeyResponse> createGenerateKeyResponse(GenerateKeyResponse value) {
        return new JAXBElement<GenerateKeyResponse>(_GenerateKeyResponse_QNAME, GenerateKeyResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InvalidateKey }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.hrp.com/", name = "invalidateKey")
    public JAXBElement<InvalidateKey> createInvalidateKey(InvalidateKey value) {
        return new JAXBElement<InvalidateKey>(_InvalidateKey_QNAME, InvalidateKey.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OneTimeLoginResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.hrp.com/", name = "oneTimeLoginResponse")
    public JAXBElement<OneTimeLoginResponse> createOneTimeLoginResponse(OneTimeLoginResponse value) {
        return new JAXBElement<OneTimeLoginResponse>(_OneTimeLoginResponse_QNAME, OneTimeLoginResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConfigureApp }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.hrp.com/", name = "configureApp")
    public JAXBElement<ConfigureApp> createConfigureApp(ConfigureApp value) {
        return new JAXBElement<ConfigureApp>(_ConfigureApp_QNAME, ConfigureApp.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConfigureAppResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.hrp.com/", name = "configureAppResponse")
    public JAXBElement<ConfigureAppResponse> createConfigureAppResponse(ConfigureAppResponse value) {
        return new JAXBElement<ConfigureAppResponse>(_ConfigureAppResponse_QNAME, ConfigureAppResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InvalidateKeyResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.hrp.com/", name = "invalidateKeyResponse")
    public JAXBElement<InvalidateKeyResponse> createInvalidateKeyResponse(InvalidateKeyResponse value) {
        return new JAXBElement<InvalidateKeyResponse>(_InvalidateKeyResponse_QNAME, InvalidateKeyResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetKeyForUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.hrp.com/", name = "getKeyForUser")
    public JAXBElement<GetKeyForUser> createGetKeyForUser(GetKeyForUser value) {
        return new JAXBElement<GetKeyForUser>(_GetKeyForUser_QNAME, GetKeyForUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OneTimeLogin }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.hrp.com/", name = "oneTimeLogin")
    public JAXBElement<OneTimeLogin> createOneTimeLogin(OneTimeLogin value) {
        return new JAXBElement<OneTimeLogin>(_OneTimeLogin_QNAME, OneTimeLogin.class, null, value);
    }

}
