package repository;

import exceptions.RepositoryException;
import model.BaseEntity;
import model.FromNode;
import model.IntoNode;
import model.validators.Validator;
import model.validators.ValidatorException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

public class XMLRepository<ID, T extends BaseEntity<ID> & FromNode & IntoNode> extends InMemoryRepository<ID, T> {
    private final String filePath;
    private final Class<T> tClass;

    public XMLRepository(Validator<T> validator, String filePath, Class<T> tClass) {
        super(validator);
        this.filePath = filePath;
        this.tClass = tClass;
        this.readFromFile();
    }

    @Override
    public Optional<T> findOne(ID id) {
        return super.findOne(id);
    }

    @Override
    public Iterable<T> findAll() {
        return super.findAll();
    }

    @Override
    public Optional<T> save(T entity) throws ValidatorException {
        Optional<T> save = super.save(entity);
        this.writeToFile();
        return save;
    }

    @Override
    public Optional<T> delete(ID id) {
        Optional<T> delete = super.delete(id);
        this.writeToFile();
        return delete;
    }

    @Override
    public Optional<T> update(T entity) throws ValidatorException {
        Optional<T> update = super.update(entity);
        this.writeToFile();
        return update;
    }

    private void readFromFile() {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder();
            Document root = builder.parse(this.filePath);
            Element rootElement = root.getDocumentElement();
            NodeList dataNodes = rootElement.getChildNodes();

            IntStream.range(0, dataNodes.getLength())
                    .forEach(i -> {
                        Node dataNode = dataNodes.item(i);
                        Optional.of(dataNode)
                                .filter(node -> node instanceof Element)
                                .map(node -> (Element) node)
                                .ifPresent(element -> {
                                    T object;

                                    try {
                                        object = this.tClass.getDeclaredConstructor().newInstance();
                                    } catch (Exception e) {
                                        throw new RepositoryException(String.format("%s", e.getMessage()));
                                    }

                                    object.fromNode(dataNode);
                                    super.save(object);
                                });
                    });

        } catch (Exception e) {
            throw new RepositoryException(String.format("Error while reading file: %s", e.getMessage()));
        }
    }

    public void writeToFile() {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder();
            Document root = builder.parse(this.filePath);
            root.removeChild(root.getChildNodes().item(0));
            Element data = root.createElement("data");

            Transformer transformer = TransformerFactory.newInstance().newTransformer();

            Iterable<T> objects = this.findAll();
            StreamSupport.stream(objects.spliterator(), false).forEach(obj -> {
                Node nodes = obj.intoNode(root);
                data.appendChild(nodes);
            });
            root.appendChild(data);

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            transformer.transform(
                    new DOMSource(root),
                    new StreamResult(new FileOutputStream(this.filePath, false))
            );
        }
        catch (Exception e) {
            throw new RepositoryException(String.format("Error while writing file: %s", e.getMessage()));
        }
    }
}
