package xyz.rootlab.app.menu.dto;

import lombok.*;
import xyz.rootlab.app.menu.entity.Menu;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MenuDto {
    private String menuCd;
    private String parntMenuCd;
    private String menuNm;
    private String menuUrl;
    private int sort;

    List<MenuDto> childMenus;

    /**
     * 메뉴 조회
     */
    public static List<MenuDto> getMenus(List<Menu> menuList) {
        List<Menu> morList = menuList.stream().filter(m -> m.getMenuCls() == 0).sorted(Comparator.comparing(Menu::getSort)).collect(Collectors.toList()); // 대분류
        List<Menu> motList = menuList.stream().filter(m -> m.getMenuCls() == 1).sorted(Comparator.comparing(Menu::getSort)).collect(Collectors.toList()); // 중분류
        List<Menu> mobList = menuList.stream().filter(m -> m.getMenuCls() == 2).sorted(Comparator.comparing(Menu::getSort)).collect(Collectors.toList()); // 소분류

        List<MenuDto> result = new ArrayList<>();

        for (Menu menuR : morList) {

            // 대분류 dto 생성
            MenuDto menuRDto = menuR.toDto();

            // 중분류 생성
            menuRDto.setChildMenus(getChildMenus(menuR.getMenuCd(), motList, mobList));

            // 리스트 추가
            result.add(menuRDto);
        }

        for (int i = 0; i < result.size(); i++) {
            if (result.get(i).childMenus.isEmpty() && !result.get(i).menuCd.equals("MOR006")) {
                result.remove(i);
            }
        }

        return result;
    }

    /**
     * 중분류 메뉴 조회
     */
    public static List<MenuDto> getChildMenus(String parntMenuCd, List<Menu> motList, List<Menu> mobList) {
        List<MenuDto> childMenusT = new ArrayList<>();

        for (Menu menuT : motList) {
            // 일치하는 경우
            if (menuT.getParntMenuCd().equals(parntMenuCd)) {

                // 중분류 dto 생성
                MenuDto menuTDto = menuT.toDto();

                // 소분류 생성
                menuTDto.setChildMenus(getChildMenus(menuTDto.menuCd, mobList));

                // 리스트 추가
                childMenusT.add(menuTDto);
            }
        }

        return childMenusT;
    }

    /**
     * 소분류 메뉴 조회
     */
    public static List<MenuDto> getChildMenus(String parntMenuCd, List<Menu> mobList) {
        List<MenuDto> childMenusB = new ArrayList<>();
        for (Menu menuB : mobList) {
            // 일치하는 경우
            if (menuB.getParntMenuCd().equals(parntMenuCd)) {

                // 소분류 dto 생성
                MenuDto menuTDto = menuB.toDto();

                // 리스트 추가
                childMenusB.add(menuTDto);
            }
        }

        return childMenusB;
    }
}
