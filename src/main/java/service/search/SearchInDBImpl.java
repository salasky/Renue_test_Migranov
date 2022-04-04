package service.search;

import java.io.*;
import java.util.*;



public class SearchInDBImpl implements SearchInDB {
    //Получить рабочий корневой каталог, в котором работает текущая программа
    String root = System.getProperty("user.dir");
    String FileName = "src\\main\\resources\\airports.dat";
    //Путь к файлу свойств
    String filePath = root + File.separator + FileName;

    Map<Integer,StringBuilder> IntMap=new TreeMap<>();
    Map<StringBuilder,StringBuilder> StringMap=new TreeMap<>();
    Map<Double, StringBuilder>  DoubleMap=new TreeMap<>();

    public StringBuilder addStringBuilder(String [] strarr){
        return new StringBuilder("Id").append(strarr[0]).append(" Name:").append(strarr[1]).append(" City:").append(strarr[2])
                .append(" Country:").append(strarr[3]).append(" IATA:").append(strarr[4]).append(" ICAO:").append(strarr[5]).
                append(" Latitude:").append(strarr[6]).append(" Longitude:").append(strarr[7]).append(" Altitude:").append(strarr[8]).
                append(" Timezone:").append(strarr[9]).append(" DST:").append(strarr[10]).append(" Tz_database:").append(strarr[11]).
                append(" Type:").append(strarr[12]).append(" Source:").append(strarr[13]).append("\n");
    }
    public StringBuilder addStringBuildernoId(String [] strarr){
        return new StringBuilder(" Name").append(strarr[1]).append(" City:").append(strarr[2])
                .append(" Country:").append(strarr[3]).append(" IATA:").append(strarr[4]).append(" ICAO:").append(strarr[5]).
                append(" Latitude:").append(strarr[6]).append(" Longitude:").append(strarr[7]).append(" Altitude:").append(strarr[8]).
                append(" Timezone:").append(strarr[9]).append(" DST:").append(strarr[10]).append(" Tz_database:").append(strarr[11]).
                append(" Type:").append(strarr[12]).append(" Source:").append(strarr[13]).append("\n");
    }

    @Override
    public void search(Integer column, String str) {
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try
        {
            br = new BufferedReader(new FileReader(filePath));
            while ((line = br.readLine()) != null)
            {
                String [] strarr=line.split(cvsSplitBy);

                switch (column){
                    case 1:
                        if(strarr[0].startsWith(str)) {
                            IntMap.put(Integer.valueOf(strarr[0]), addStringBuilder(strarr));
                        }
                    break;

                    case 2:
                        if(strarr[1].startsWith(str)) {
                            StringMap.put(new StringBuilder(strarr[1]), addStringBuilder(strarr));
                        }
                        break;

                    case 3:
                        if(strarr[2].startsWith(str)) {
                            StringMap.put(new StringBuilder(strarr[2]).append(" Id:").append(strarr[0]), addStringBuildernoId(strarr));
                        }
                        break;

                    case 4:
                        if(strarr[3].startsWith(str)) {
                            StringMap.put(new StringBuilder(strarr[3]).append(" Id:").append(strarr[0]), addStringBuildernoId(strarr));
                        }
                        break;
                    case 5:
                        if(strarr[4].startsWith(str)) {
                            StringMap.put(new StringBuilder(strarr[4]).append(" Id:").append(strarr[0]), addStringBuildernoId(strarr));
                        }
                        break;
                    case 6:
                        if(strarr[5].startsWith(str)) {
                            StringMap.put(new StringBuilder(strarr[5]).append(" Id:").append(strarr[0]), addStringBuildernoId(strarr));
                        }
                        break;

                    case 7:
                        if(strarr[6].startsWith(str)) {
                            DoubleMap.put(Double.valueOf(strarr[6]), addStringBuilder(strarr));
                        }
                        break;
                    case 8:
                        if(strarr[7].startsWith(str)) {
                            DoubleMap.put(Double.valueOf(strarr[7]), addStringBuilder(strarr));
                        }
                        break;

                    //  думаю эту проблему можно решить своим компоратором
                    //1919 Id:8943= Name"Cuamba Airport"
                    // 192 Id:3429= Name"Iliamna Airport"
                    case 9:
                        if(strarr[8].startsWith(str)) {
                            StringMap.put(new StringBuilder(strarr[8]).append(" Id:").append(strarr[0]), addStringBuildernoId(strarr));
                        }
                        break;
                    //так же нужен свой компоратор
                    case 10:
                        if(strarr[9].startsWith(str)) {
                            StringMap.put(new StringBuilder(strarr[9]).append(" Id:").append(strarr[0]), addStringBuildernoId(strarr));
                        }
                        break;

                    case 11:
                        if(strarr[10].startsWith(str)) {
                            StringMap.put(new StringBuilder(strarr[10]).append(" Id:").append(strarr[0]), addStringBuildernoId(strarr));
                        }
                        break;
                    case 12:
                        if(strarr[11].startsWith(str)) {
                            StringMap.put(new StringBuilder(strarr[11]).append(" Id:").append(strarr[0]), addStringBuildernoId(strarr));
                        }
                        break;
                    case 13:
                        if(strarr[12].startsWith(str)) {
                            StringMap.put(new StringBuilder(strarr[12]).append(" Id:").append(strarr[0]), addStringBuildernoId(strarr));
                        }
                        break;
                    case 14:
                        if(strarr[13].startsWith(str)) {
                            StringMap.put(new StringBuilder(strarr[13]).append(" Id:").append(strarr[0]), addStringBuildernoId(strarr));
                        }
                        break;
                }

            }

            switch (column){
                case 1:
                    System.out.println(IntMap);
                    System.out.println("Количество найденных строк: "+IntMap.size());
                    IntMap.clear();
                    break;
                case 2: case 3: case 4: case 5: case 6: case 9: case 10: case 11:case 12:case 13:case 14:
                    System.out.println(StringMap);
                    System.out.println("Количество найденных строк: "+StringMap.size());
                    StringMap.clear();
                    break;
                case 7: case 8:
                    System.out.println(DoubleMap);
                    System.out.println("Количество найденных строк: "+DoubleMap.size());
                    DoubleMap.clear();
                    break;

            }



        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        finally
        {
            if (br != null)
            {
                try
                {
                    br.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}










