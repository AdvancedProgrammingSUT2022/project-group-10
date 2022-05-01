package Model.UnitPackage;

import Model.Map.Resource;
import Model.Technology;

public enum UnitType {
    ARCHER(70, 2, null, Technology.ARCHERY),
    CHARIOT_ARCHER(60, 4, Resource.HORSE, Technology.WHEEL),
    SCOUT(25, 2, null, null),
    SETTLER(89, 2, null, null),
    SPEARMAN(50, 2, null, Technology.BRONZE_WORKING),
    WARRIOR(40, 2, null, null),
    WORKER(70, 2, null, null),
    CATAPULT(100, 2, Resource.IRON, Technology.MATH),
    HORSEMAN(80, 4, Resource.HORSE, Technology.HORSE_RIDING),
    SWORDSMAN(80, 2, Resource.IRON, Technology.IRON_WORKING),
    CROSSBOWMAN(120, 2, null, Technology.MACHINERY),
    KNIGHT(150, 3, Resource.HORSE, Technology.CHIVALRY),
    LONGSWORDSMAN(150, 3, Resource.IRON, Technology.STEEL),
    PIKEMAN(100, 2, null, Technology.CIVIL_SERVICE),
    TREBUCHET(170, 2, Resource.IRON, Technology.PHYSICS),
    CANNON(250, 2, null, Technology.CHEMISTRY),
    CAVALRY(250, 3, Resource.HORSE, Technology.MILITARY_SCIENCE),
    LANCER(220, 4, Resource.HORSE, Technology.METALLURGY),
    MUSKETMAN(120, 2, null, Technology.GUNPOWDER),
    RIFLEMAN(200, 2, null, Technology.RIFLING),
    ANTI_TANK_GUN(300, 2, null, Technology.REPLACEABLE_PARTS),
    ARTILLERY(420, 2, null, Technology.DYNAMITE),
    INFANTRY(300, 2, null, Technology.REPLACEABLE_PARTS),
    PANZER(450, 5, null, Technology.COMBUSTION),
    TANK(450, 4, null, Technology.COMBUSTION);

    private final int MP;
    private final int cost;
    private final Resource resource;
    private final Technology technology;

    UnitType (int cost, int MP, Resource resource, Technology technology) {
        this.MP = MP;
        this.cost = cost;
        this.resource = resource;
        this.technology = technology;
    }

    public int getCost() {
        return cost;
    }
    public int getMP() {
        return MP;
    }
    public Resource getResource() {
        return resource;
    }
    public Technology getTechnology() {
        return technology;
    }
}
