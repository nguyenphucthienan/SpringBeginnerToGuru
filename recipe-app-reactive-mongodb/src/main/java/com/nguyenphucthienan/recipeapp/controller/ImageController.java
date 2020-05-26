package com.nguyenphucthienan.recipeapp.controller;

import com.nguyenphucthienan.recipeapp.service.ImageService;
import com.nguyenphucthienan.recipeapp.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ImageController {

    private final RecipeService recipeService;
    private final ImageService imageService;

    public ImageController(RecipeService recipeService, ImageService imageService) {
        this.recipeService = recipeService;
        this.imageService = imageService;
    }

    @GetMapping("/recipe/{id}/image-upload")
    public String showUploadForm(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.findCommandById(id));
        return "recipe/image-upload-form";
    }

    @PostMapping("/recipe/{id}/image")
    public String handlePostImage(@PathVariable String id, @RequestParam("imagefile") MultipartFile file) {
        imageService.saveImageFile(id, file).block();
        return "redirect:/recipe/" + id + "/show";
    }

    // @GetMapping("/recipe/{id}/image")
    // public void renderImageFromDb(@PathVariable String id, HttpServletResponse response) throws IOException {
    //     RecipeCommand recipeCommand = recipeService.findCommandById(id).block();
    //     byte[] byteArray = new byte[Objects.requireNonNull(recipeCommand).getImage().length];
    //
    //     int i = 0;
    //     for (Byte wrappedByte : recipeCommand.getImage()) {
    //         byteArray[i++] = wrappedByte; // Auto unboxing
    //     }
    //
    //     response.setContentType("image/jpeg");
    //     InputStream inputStream = new ByteArrayInputStream(byteArray);
    //     IOUtils.copy(inputStream, response.getOutputStream());
    // }
}
