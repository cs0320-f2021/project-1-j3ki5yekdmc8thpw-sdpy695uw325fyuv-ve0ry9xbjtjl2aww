import org.eclipse.jetty.xml.XmlParser;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.UserDataHandler;

public class KD_node implements Node {
  private double[] coords_;
  private KD_node left_ = null;
  private KD_node right_ = null;

  public KD_node (double[] coords) {
    coords_ = coords;
  }

//  public KD_node(double x, double y) {
//    this(new double[]{x, y});
//  }
//
//  public KD_node(double x, double y, double z) {
//    this(new double[]{x, y, z});
//  }

  //returns a single coordinate
  public double getCoord(int index) {
    return coords_[index];
  }

  //returns the entire coordinate set
  public double[] getAllCoords() {
    return coords_;
  }

  //computes and returns euclidean distance from self to another node
  private double distance(KD_node node) {
    double dist = 0;
    for (int i = 0; i < coords_.length; i++) {
      double d = coords_[i] - node.getCoord(i);
      dist += d * d;
    }
    return Math.sqrt(dist);
  }

  public KD_node getLeft(){
    return left_;
  }

  public KD_node getRight(){
    return right_;
  }

  public void setLeft(KD_node n){
    left_ = n;
  }

  public void setRight(KD_node n){
    right_ = n;
  }

  public void setCoord(int index, double d){
    coords_[index] = d;
  }

  @Override
  public String getNodeName() {
    return null;
  }

  @Override
  public String getNodeValue() throws DOMException {
    return null;
  }

  @Override
  public void setNodeValue(String nodeValue) throws DOMException {

  }

  @Override
  public short getNodeType() {
    return 0;
  }

  @Override
  public Node getParentNode() {
    return null;
  }

  @Override
  public NodeList getChildNodes() {
    return null;
  }

  @Override
  public Node getFirstChild() {
    return null;
  }

  @Override
  public Node getLastChild() {
    return null;
  }

  @Override
  public Node getPreviousSibling() {
    return null;
  }

  @Override
  public Node getNextSibling() {
    return null;
  }

  @Override
  public NamedNodeMap getAttributes() {
    return null;
  }

  @Override
  public Document getOwnerDocument() {
    return null;
  }

  @Override
  public Node insertBefore(Node newChild, Node refChild) throws DOMException {
    return null;
  }

  @Override
  public Node replaceChild(Node newChild, Node oldChild) throws DOMException {
    return null;
  }

  @Override
  public Node removeChild(Node oldChild) throws DOMException {
    return null;
  }

  @Override
  public Node appendChild(Node newChild) throws DOMException {
    return null;
  }

  @Override
  public boolean hasChildNodes() {
    return false;
  }

  @Override
  public Node cloneNode(boolean deep) {
    return null;
  }

  @Override
  public void normalize() {

  }

  @Override
  public boolean isSupported(String feature, String version) {
    return false;
  }

  @Override
  public String getNamespaceURI() {
    return null;
  }

  @Override
  public String getPrefix() {
    return null;
  }

  @Override
  public void setPrefix(String prefix) throws DOMException {

  }

  @Override
  public String getLocalName() {
    return null;
  }

  @Override
  public boolean hasAttributes() {
    return false;
  }

  @Override
  public String getBaseURI() {
    return null;
  }

  @Override
  public short compareDocumentPosition(Node other) throws DOMException {
    return 0;
  }

  @Override
  public String getTextContent() throws DOMException {
    return null;
  }

  @Override
  public void setTextContent(String textContent) throws DOMException {

  }

  @Override
  public boolean isSameNode(Node other) {
    return false;
  }

  @Override
  public String lookupPrefix(String namespaceURI) {
    return null;
  }

  @Override
  public boolean isDefaultNamespace(String namespaceURI) {
    return false;
  }

  @Override
  public String lookupNamespaceURI(String prefix) {
    return null;
  }

  @Override
  public boolean isEqualNode(Node arg) {
    return false;
  }

  @Override
  public Object getFeature(String feature, String version) {
    return null;
  }

  @Override
  public Object setUserData(String key, Object data, UserDataHandler handler) {
    return null;
  }

  @Override
  public Object getUserData(String key) {
    return null;
  }

//
//  public String toString() {
//    StringBuilder s = new StringBuilder("(");
//    for (int i = 0; i < coords_.length; ++i) {
//      if (i > 0)
//        s.append(", ");
//      s.append(coords_[i]);
//    }
//    s.append(')');
//    return s.toString();
//  }
}

