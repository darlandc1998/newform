package com.example.newform.enums;

public enum MesEnum {

    JANEIRO("Janeiro",0),
    FEVEREIRO("Fevereiro",1),
    MARCO("Março",2),
    ABRIL("Abril",3),
    MAIO("Maio",4),
    JUNHO("Junho",5),
    JULHO("Julho",6),
    AGOSTO("Agosto",7),
    SETEMBRO("Setembro",8),
    OUTUBRO("Outubro",9),
    NOVEMBRO("Novembro",10),
    DEZEMBRO("Dezembro",11);

    private String description;
    private int value;

    MesEnum(String description, int value){
        this.description = description;
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public static MesEnum getEnumFromDescription(String description){
        return getEnumFromDescription(description, null);
    }

    public static MesEnum getEnumFromDescription(String description, MesEnum defaultValue){
        for (MesEnum mes: MesEnum.values()){
            if (mes.getDescription().equals(description)){
                return mes;
            }
        }

        return defaultValue;
    }

    @Override
    public String toString() {
        return  description ;
    }
}
