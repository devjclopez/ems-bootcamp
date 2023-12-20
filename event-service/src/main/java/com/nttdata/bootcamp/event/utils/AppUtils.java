package com.nttdata.bootcamp.event.utils;

import com.nttdata.bootcamp.event.dto.CategoryDto;
import com.nttdata.bootcamp.event.model.Category;
import org.springframework.beans.BeanUtils;

public class AppUtils {
  public static CategoryDto entityToDto(Category category) {
    CategoryDto categoryDto = new CategoryDto();
    BeanUtils.copyProperties(category, categoryDto);
    return categoryDto;
  }

  public static Category dtoToEntity(CategoryDto categoryDto) {
    Category category = new Category();
    BeanUtils.copyProperties(categoryDto, category);
    return category;
  }
}
