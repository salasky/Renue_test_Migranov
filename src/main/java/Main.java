


import service.search.SearchInDB;
import service.search.SearchInDBImpl;
import service.yamlEditor.YamlEditor;
import service.yamlEditor.YamlEditorImpl;


import java.io.IOException;
import java.lang.annotation.*;
import java.util.*;

public class Main {
    private static YamlEditor yamlEditor;
    private static SearchInDB searchInDB;

    public Main(YamlEditor yamlEditor, SearchInDB searchInDB) {
        this.yamlEditor = yamlEditor;
        this.searchInDB=searchInDB;
    }
    public static void main(String[] args) {
        Main main=new Main(new YamlEditorImpl(),new SearchInDBImpl());
        Boolean flag=true;


        while (flag) {
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("|     1      |   2  |   3  |    4     |   5  |   6  |     7    |     8     |     9    |    10    |  11 |     12     |  13  |   14   |");
            System.out.println("| Airline ID | Name | City | Country  | IATA | ICAO | Latitude | Longitude | Altitude | Timezone | DST | Tz_database| Type | Source |");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");

            Integer findColumn = Integer.valueOf(yamlEditor.getColumn());
            System.out.println("Поиск осуществляется по столбцу №: " + findColumn + "    для изменения № столбца наберите: edit, для выхода из приложения наберите: exit");

            Scanner in = new Scanner(System.in);
            System.out.println("Введите строку для поиска: ");
            String str = in.nextLine();

            switch (str) {
                case "exit": case "EXIT":
                    flag = false;
                    break;

                case "edit": case "EDIT":
                    System.out.println("Введите номер столбца для поиска от 1 до 14");
                    try {
                        findColumn = in.nextInt();
                    } catch (Exception e) {
                        System.out.println("Введен невыерный номер!");
                    }
                    while (findColumn > 14 || findColumn < 1) {
                        System.out.println("Введен неверный номер столбца.Пожалуйста,повторите попытку:");
                        findColumn = in.nextInt();
                    }
                    yamlEditor.setColumn(findColumn);
                    break;

                default:
                    System.out.println("Результат поиска по ключу: "+str+" в колонке "+findColumn);

                    //костыль
                    if(findColumn==2 || findColumn==3 || findColumn==4 || findColumn==5|| findColumn==6
                            || findColumn==11 || findColumn==12 || findColumn==12 || findColumn==13 || findColumn==14){
                        StringBuffer stringBuffer=new StringBuffer();
                        stringBuffer.append(str);
                        stringBuffer.insert(0,"\"");
                        str=String.valueOf(stringBuffer);
                    }

                    if(str.isBlank()){
                        System.out.println("Пожалуйста,введите строку");
                    }
                    else {

                        long time = System.nanoTime();
                        try {
                            searchInDB.search(findColumn,str);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        time = System.nanoTime() - time;
                        System.out.printf("Время поиска: "+ time/1_000_000.0+" мс\n");
                        Runtime runtime = Runtime.getRuntime();
                        long mb = 1024 * 1024;
                        long total = runtime.totalMemory();
                        long free = runtime.freeMemory();
                        System.out.println( ("Используется памяти "+(total - free) / mb)+" Мб");
                    }
                                        System.out.println("Нажмите «ENTER» для продолжения");
                    in.nextLine();
                    break;
            }
        }
    }
}



