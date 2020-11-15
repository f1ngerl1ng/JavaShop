package org.example.controller;

import org.example.dao.ProductDao;
import org.example.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;


import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

@Controller
public class AdminController {

    private Path path;
    @Autowired
    private ProductDao productDao;

    @RequestMapping("/admin")
    public String adminPage(){
        return "admin";
    }

    @RequestMapping("/admin/productInventory")
    public String productInventory(Model model){
        List<Product> products = productDao.getAllProducts();
        model.addAttribute("products", products);
        return "productInventory";
    }

    @RequestMapping("/admin/productInventory/deleteProduct/{id}")
    public  String deleteProduct(@PathVariable String id) {
        productDao.delProduct(id);
        return "redirect:/admin/productInventory";
    }

    @RequestMapping("/admin/productInventory/addProduct")
    private String addProduct(Model model){
        Product product = new Product();
        product.setProductCategory("Sweter");
        product.setProductCondition("new");
        product.setProductStatus("active");
        model.addAttribute("product", product);
        return "addProduct";
    }

    @RequestMapping(value ="/admin/productInventory/addProduct", method = RequestMethod.POST)
    public String addProductPost(@ModelAttribute("product") Product product, HttpServletRequest request){
        productDao.addProduct(product);
        MultipartFile productImage = product.getProductImage();
        System.out.println("productImage => " + productImage);
        String rootDirectory = request.getSession().getServletContext().getRealPath("/");
        path = Paths.get(rootDirectory + "\\WEB-INF\\resources\\images\\"+product.getProductId()+".png");

        if (productImage != null && !productImage.isEmpty()) {
            try {
                productImage.transferTo(new File(path.toString()));
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Product image saving failed", e);
            }
        }
        return "redirect:/admin/productInventory";
    }

    @RequestMapping(value = "/editProduct/{id}")
    public ModelAndView editProduct(@PathVariable("id") String id, Model model) {
        List<Category> categories=categoryDao.getCategoryList();
        model.addAttribute("product",productDao.getProductById(id));
        model.addAttribute("categories",categories);
        return new ModelAndView("editProduct");
    }

    @RequestMapping(value = "/editProduct", method = RequestMethod.POST)
    public RedirectView editProduct(@ModelAttribute("product") Product product, Model model, HttpServletRequest
            request ) {
        MultipartFile productImage = product.getImage();
        String rootDirectory = request.getSession().getServletContext().getRealPath("/");
        path = Paths.get(rootDirectory + "\\WEB-INF\\resources\\Uploads\\"+product.getId()+".png");
        if (productImage != null && !productImage.isEmpty()) {
            try {
                File file = new File(path.toString());
                if(file.delete())
                    productImage.transferTo(new File(path.toString()));
            } catch (Exception e) {
                throw new RuntimeException("Product image saving failed" , e);
            }
        }
        product.setCategory( categoryDao.getCategoryById(product.getCategory().getId()));
        productDao.editProduct(product);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/admin");
        return redirectView;
    }
}
