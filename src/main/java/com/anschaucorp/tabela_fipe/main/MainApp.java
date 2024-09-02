package com.anschaucorp.tabela_fipe.main;

import com.anschaucorp.tabela_fipe.model.GenericData;
import com.anschaucorp.tabela_fipe.model.ModelData;
import com.anschaucorp.tabela_fipe.model.VehicleData;
import com.anschaucorp.tabela_fipe.service.APIUsage;
import com.anschaucorp.tabela_fipe.service.DataConverter;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MainApp {

    private Scanner scanner = new Scanner(System.in);
    private APIUsage apiUsage = new APIUsage();
    private DataConverter dataConverter = new DataConverter();

    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";

    public void showMenu(){
        var menu = """
                =======> TABELA FIPE <=======
                ===>>OPÇÕES<<===
                Carro
                Moto
                Caminhão
                
                Digite uma opção para consulta:                
                """;

        System.out.println(menu);

        var option = scanner.nextLine();

        String url;

        if (option.toLowerCase().contains("carr")){
            url = URL_BASE + "carros/marcas/";
        } else if (option.toLowerCase().contains("mot")) {
            url = URL_BASE + "motos/marcas/";
        } else {
            url = URL_BASE + "caminhoes/marcas/";
        }

        var json = apiUsage.getAPIData(url);

        //System.out.println(json);

        var manufacturer = dataConverter.getList(json, GenericData.class);
        manufacturer.stream()
                .sorted(Comparator.comparing(GenericData::description))
                .forEach(g -> System.out.println(g.description().toUpperCase() + " - code: "+ g.code()));

        System.out.println("\nInforme o código da marca para consultar os modelos: ");

        var codeModel = scanner.nextLine();

        url = url + codeModel + "/modelos/";

        json = apiUsage.getAPIData(url);

        //System.out.println(json);

        var models = dataConverter.getData(json, ModelData.class);

        System.out.println("\nModelos desta marca: ");
        models.models().stream()
                .sorted(Comparator.comparing(GenericData::description))
                .forEach(g -> System.out.println(g.description().toUpperCase() + " - code: "+ g.code()));

        System.out.println("\nInforme o um trecho do veículo que deseja consultar: ");

        var vehicle = scanner.nextLine();

        List<GenericData> filteredVehicles = models.models().stream()
                .filter(g -> g.description().toLowerCase().contains(vehicle.toLowerCase()))
                .collect(Collectors.toList());

        filteredVehicles.stream()
                .sorted(Comparator.comparing(GenericData::description))
                .forEach(g -> System.out.println(g.description().toUpperCase() + " - code: "+ g.code()));

        System.out.println("\nInforme o código do veículo que deseja consultar: ");

        var codeVehicle = scanner.nextLine();

        url = url + codeVehicle + "/anos/";

        json = apiUsage.getAPIData(url);

        var listYears = dataConverter.getList(json, GenericData.class);

//        listYears.stream()
//                .sorted(Comparator.comparing(GenericData::code))
//                .forEach(g -> System.out.println(g.description().toUpperCase() + " - code: "+ g.code()));

        List<VehicleData> vehicleDataList =new ArrayList<>();

        String url2 = url;

        listYears.stream()
                .forEach(g -> vehicleDataList.add(dataConverter.getData(apiUsage.getAPIData(url2+g.code()), VehicleData.class)));

        vehicleDataList.stream()
                .sorted(Comparator.comparing(VehicleData::year).reversed())
                .forEach(System.out::println);


    }

}
