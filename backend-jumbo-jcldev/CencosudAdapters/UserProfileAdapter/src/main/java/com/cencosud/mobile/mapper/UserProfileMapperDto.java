package com.cencosud.mobile.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.cencosud.mobile.dto.profile.jumbo.CategoryDto;
import com.cencosud.mobile.dto.profile.jumbo.FavoritesDto;
import com.cencosud.mobile.dto.profile.jumbo.JumboUserProfileDto;
import com.cencosud.mobile.dto.profile.jumbo.UserProfileDto;
import com.cencosud.mobile.model.category.Category;
import com.cencosud.mobile.model.category.Favorites;
import com.cencosud.mobile.model.profile.jumbo.UserProfile;
import com.cencosud.mobile.model.profile.jumbo.UserProfileHomolog;

@Mapper
public interface UserProfileMapperDto {
    UserProfileMapperDto INSTANCE = Mappers.getMapper(UserProfileMapperDto.class);

    UserProfileHomolog userProfileToUserProfileDto(UserProfile userProfile);

    CategoryDto categoryToCategoryDto(Category category);

    CategoryDto stringToCategoryDto(String category);

    FavoritesDto categoryToCategoryDto(Favorites favorites);

    UserProfileDto userProfileHomologToUserDto(UserProfileHomolog profile);

    @Mapping(target = "defaultSalesChannel", ignore = true)
    JumboUserProfileDto userProfileHomologToJumboDto(UserProfileHomolog profile);
}
