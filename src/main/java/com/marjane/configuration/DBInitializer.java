package com.marjane.configuration;

//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityManagerFactory;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//
//import java.io.*;
//import java.net.URL;
//import java.util.List;
//import java.util.Properties;

//import static com.panov.store.common.Constants.STATIC_IMAGES_FOLDER;

/**
 * If {@code fillDatabaseWithInitialData} property in META-INF/app.properties is equal
 * to true, then this method will seed the database with initial data.
 *
 * @author Maksym Panov
 * @version 1.0
 */

//@Configuration
//@RequiredArgsConstructor
public class DBInitializer {
//    private final EntityManagerFactory emf;
//    private final DAO<Order> orderRepository;
//    private final UnregisteredCustomerService unregisteredCustomerService;
//
//    @Bean
//    public void seedDatabase() {
//        File imageDirectory = new File(STATIC_IMAGES_FOLDER);
//        if (!imageDirectory.exists() || !imageDirectory.isDirectory()) {
//            imageDirectory.mkdir();
//        }
//
//        EntityManager em = emf.createEntityManager();
//        try {
//            ClassPathResource cpr = new ClassPathResource("META-INF/app.properties");
//            File file = cpr.getFile();
//            if (!file.exists())
//                return;
//            Properties properties = new Properties();
//            InputStream fis = new FileInputStream(file);
//            properties.load(fis);
//            boolean seed = Boolean.parseBoolean(properties.getProperty("fillDatabaseWithInitialData"));
//            fis.close();
//            if (!seed)
//                return;
//
//            em.getTransaction().begin();
//            seedProductTypes(em);
//            seedProducts(em);
//            seedUsers(em);
//            seedDeliveryTypes(em);
//            em.getTransaction().commit();
//
//            seedOrders();
//            System.out.println("DATABASE HAS BEEN SUCCESSFULLY SEEDED");
//
//        } catch (Exception e) {
//            System.out.println("COULD NOT SEED THE DATABASE");
//            e.printStackTrace();
//        }
//    }
//
//    private <T> List<T> getList(String filename, Class<T> classname) throws IOException {
//        URL url = getClass().getClassLoader().getResource("META-INF/initial/" + filename);
//        ObjectMapper mapper = new ObjectMapper();
//        var reader = mapper.readerForListOf(classname);
//        return (List<T>) reader.readValue(url);
//    }
//
//    private void seedProductTypes(EntityManager em) throws IOException {
//        List<ProductType> productTypes = getList("product_types.json", ProductType.class);
//
//        productTypes.forEach(em::persist);
//    }
//
//    private void seedProducts(EntityManager em) throws IOException {
//        List<Product> products = getList("products.json", Product.class);
//
//        products.forEach(em::persist);
//
//        products.stream()
//                .map(Product::getImage)
//                .forEach(this::transferInitialImageToFilesystem);
//    }
//
//    private void seedUsers(EntityManager em) throws IOException {
//        List<User> users = getList("users.json", User.class);
//
//        users.forEach(em::persist);
//
//        users.stream()
//                .map(User::getImage)
//                .forEach(this::transferInitialImageToFilesystem);
//    }
//
//    private void seedDeliveryTypes(EntityManager em) throws IOException {
//        List<DeliveryType> deliveryTypes = getList("delivery_types.json", DeliveryType.class);
//
//        deliveryTypes.forEach(em::persist);
//    }
//
//    private void seedOrders() throws IOException {
//        List<Order> orders = getList("orders.json", Order.class);
//
//        orders.forEach(o -> {
//            if (o.getUnregisteredCustomer() != null) {
//                unregisteredCustomerService.createUnregisteredCustomer(
//                        o.getUnregisteredCustomer()
//                );
//            }
//            orderRepository.insert(o);
//        });
//    }
//
//    private void transferInitialImageToFilesystem(String imageName) {
//        if (imageName == null) {
//            return;
//        }
//
//        try (
//                InputStream is =
//                        getClass()
//                                .getClassLoader()
//                                .getResourceAsStream("META-INF/initial/" + imageName);
//                OutputStream os =
//                        new FileOutputStream(
//                                STATIC_IMAGES_FOLDER + "/" + imageName
//                        )
//        ) {
//            if (is == null) {
//                return;
//            }
//            os.write(is.readAllBytes());
//        } catch(Exception ignored) {}
//    }
}
