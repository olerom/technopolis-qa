# technopolis-qa

Домашняя работа по курсу "Обеспечение качества в разработке программного обеспечения".

## GroupCreationTest

Тестирование создание группы с типом "Публичная страница". Соответствие названия и описания.

1. Регистрируемся
    1. Поле для логина берем по id равному `field_email`
    2. Поле пароля берем по id равному `field_password`
2. Переходим на страницу с группами по XPath равному 
`.//*[contains(@id,'hook_Block_MiddleColumnTopCard_MenuUser')]//*[@class='mctc_navMenuSec' and contains(@href,'groups')]`
3. Открываем форму для создания группы по XPath равному `.//*[contains(@href,'st.layer.cmd=PopLayerCreateAltGroup')]`
4. Выбираем группу с типом "Публичная страница" по XPath равному `.//*[contains(@class,'create-group-dialog_img __page')]`
5. Название группы, будем брать по id `field_name`
6. Описание группы, по id `field_description`
7. Создаем группу, также берем по id `hook_FormButton_button_create`
8. Проверяем, что открылась страница группы
9. Проверяем соответсвие введенного названия. Сравниваем значение по XPath'y 
`.//*[contains(@class,'mctc-top')]//*[contains(@class,'mctc_name_tx')]` 
с введенным. Должны быть равны.
10. Проверяем соответсвие введенного описания. Сравниваем значение по XPath'y 
`.//*[contains(@class,'group-info_cnt')]//*[contains(@class,'group-info_desc')]`
с введенным. Должны быть равны.

## PostCreationTest

Тестирования создания поста на стене группы.

1. Повторяем пункты 1-2 из GroupCreationTest
2. Открываем группу по XPath'у 
`.//*[contains(@id,'hook_Block_MyGroupsNavBlock')]//*[contains(@href, '/group/55192157552653')]`,
где `/group/55192157552653` - ид группы
3. Выбираем поле для создания поста по XPath'у `.//*[contains(@class,'input_placeholder')]`
4. Текст поста по id `posting_form_text_field`
5. Делимся темой по XPath'у `.//*[contains(@class,'button-pro')]`
6. По XPath'у 
`.//*[contains(@class,'portlet_b')]//*[contains(@class,'media-text_cnt_tx textWrap')]`
получаем список постов. 
Нужен первый, так как проверяем только что созданный пост (полагаем, что за это время новый пост не появится).
И сравниваем значение с введенным.

## GroupCreationInvalidValuesTest
Проверяем создание группы при невалидных данных. 
Например, остутствие названия.

1. Повторяем пункты 1-4 из GroupCreationTest
2. Создаем группу по id `hook_FormButton_button_create`
3. Проверяем, есть ли элемент с XPath'ом 
`.//*[contains(@class,'form_i__error')]`.
Если да, то все правильно.
TODO: check all the fields

## GroupDeletionTest
Проверяем удаление группы

1. Повторяем пункты 1-2 из PostCreationTest
2. Удаляем по XPath'у 
`.//*[contains(@class,'tico_img ic ic_delete')]`
3. Проверяем, что перешли на страницу.