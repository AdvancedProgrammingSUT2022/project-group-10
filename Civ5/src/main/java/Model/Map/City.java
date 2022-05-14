package Model.Map;

import Controller.GameController;
import Model.Civilization;
import Model.UnitPackage.UnitStatus;
import Model.UnitPackage.UnitType;

import java.util.ArrayList;
import java.util.HashMap;

public class City {
    private ArrayList<Tile> tiles;
    private Civilization civilization;
    private int storedFood;
    private int goldPerTurn;
    private int foodPerTurn;
    private int productionPerTurn;
    private int sciencePerTurn;
    private HashMap<UnitType, Integer> lastCostsUntilNewProductions;
    private UnitType inProgressUnit;
    private ArrayList<Citizen> citizens;
    private int turnsUntilBirthCitizen;
    private int turnsUntilDeathCitizen;
    private int citizenNecessityFood;
    private int gainCitizenLastFood;
    private int lostCitizenLastFood;
    private int turnsUntilGrowthBorder;
    private int borderExpansionCost;
    private int borderLastCost;
    private double HP;
    private double combatStrength;
    private double rangedCombatStrength;
    private CityStatus cityStatus;
    private final String name;

    public City(Civilization civilization, Tile centerTile, String name) {
        lastCostsUntilNewProductions = new HashMap<>();
        tiles = new ArrayList<>();
        tiles.add(centerTile);
        tiles.addAll(centerTile.getNeighbors());

        for (Tile tile : tiles)
            tile.setCity(this);

        this.civilization = civilization;
        if (civilization.getCities().isEmpty()) cityStatus = CityStatus.CAPITAL;
        else cityStatus = CityStatus.NORMAL;
        civilization.addCity(this);
        this.citizens = new ArrayList<>();
        Citizen citizen = new Citizen(this, centerTile);
        centerTile.setWorkingCitizen(citizen);
        this.citizens.add(citizen);
        this.citizens.add(new Citizen(this, null));
        this.citizenNecessityFood = 10;
        this.gainCitizenLastFood = 10;
        this.lostCitizenLastFood = 10;
        this.borderExpansionCost = 50;
        this.borderLastCost = 50;
        this.HP = 20;
        if (this.tiles.get(0).getType().equals(TerrainType.HILL)) this.combatStrength = 18;
        else this.combatStrength = 15;
        this.rangedCombatStrength = 0;
        this.name = name;
    }

    public void setInProgressUnit (UnitType unitType) {
        this.inProgressUnit = unitType;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public Civilization getCivilization() {
        return civilization;
    }

    public void setCivilization (Civilization civilization) {
        this.civilization = civilization;
    }

    public int getStoredFood() {
        return storedFood;
    }

    public int getFoodPerTurn() {
        return foodPerTurn;
    }

    public int getProductionPerTurn() {
        return productionPerTurn;
    }

    public int getGoldPerTurn() {
        return goldPerTurn;
    }

    public int getSciencePerTurn() {
        return sciencePerTurn;
    }

    public int getTurnsUntilBirthCitizen() {
        return turnsUntilBirthCitizen;
    }

    public int getTurnsUntilDeathCitizen() {
        return turnsUntilDeathCitizen;
    }

    public int getTurnsUntilGrowthBorder() {
        return turnsUntilGrowthBorder;
    }

    public int getCitizenNecessityFood() {
        return citizenNecessityFood;
    }

    public int getGainCitizenLastFood() {
        return gainCitizenLastFood;
    }

    public int getLostCitizenLastFood() {
        return lostCitizenLastFood;
    }

    public int getBorderExpansionCost() {
        return borderExpansionCost;
    }

    public int getBorderLastCost() {
        return borderLastCost;
    }

    public HashMap<UnitType, Integer> getLastCostsUntilNewProductions() {
        return lastCostsUntilNewProductions;
    }

    public UnitType getInProgressUnit() {
        return inProgressUnit;
    }

    public ArrayList<Citizen> getCitizens() {
        return citizens;
    }

    public double getHP() { //TODO add walls
        return HP;
    }

    public void setHP (double HP) {
        this.HP = HP;
    }

    public double getCombatStrength() {
        double defaultCombatStrength = combatStrength;

        defaultCombatStrength += this.citizens.size();
        defaultCombatStrength += garrisonBonus();

        return defaultCombatStrength;
    }

    private double garrisonBonus() {
        Tile centerTile = this.tiles.get(0);
        if (centerTile.getMilitary() != null && centerTile.getMilitary().getStatus().equals(UnitStatus.GARRISON))
            return centerTile.getMilitary().getCombatStrength();
        return 0;
    }

    public double getRangedCombatStrength() {
        return rangedCombatStrength;
    }

    public CityStatus getCityStatus() {
        return cityStatus;
    }

    public void setStoredFood(int storedFood) {
        this.storedFood = storedFood;
    }

    public void setFoodPerTurn(int foodPerTurn) {
        this.foodPerTurn = foodPerTurn;
    }

    public void setProductionPerTurn(int productionPerTurn) {
        this.productionPerTurn = productionPerTurn;
    }

    public void setGoldPerTurn(int goldPerTurn) {
        this.goldPerTurn = goldPerTurn;
    }

    public void setSciencePerTurn(int sciencePerTurn) {
        this.sciencePerTurn = sciencePerTurn;
    }


    public void setCitizenNecessityFood(int citizenNecessityFood) {
        this.citizenNecessityFood = citizenNecessityFood;
    }

    public void setGainCitizenLastFood(int gainCitizenLastFood) {
        this.gainCitizenLastFood = gainCitizenLastFood;
    }

    public void setLostCitizenLastFood(int lostCitizenLastFood) {
        this.lostCitizenLastFood = lostCitizenLastFood;
    }

    public void setTurnsUntilGrowthBorder(int turnsUntilGrowthBorder) {
        this.turnsUntilGrowthBorder = turnsUntilGrowthBorder;
    }

    public void setBorderExpansionCost(int borderExpansionCost) {
        this.borderExpansionCost = borderExpansionCost;
    }

    public void setBorderLastCost(int borderLastCost) {
        this.borderLastCost = borderLastCost;
    }

    public void setTurnsUntilBirthCitizen(int turnsUntilBirthCitizen) {
        this.turnsUntilBirthCitizen = turnsUntilBirthCitizen;
    }

    public void setTurnsUntilDeathCitizen(int turnsUntilDeathCitizen) {
        this.turnsUntilDeathCitizen = turnsUntilDeathCitizen;
    }

    public void updateStoredFood() {
        int food = this.foodPerTurn - ((this.citizens.size() - 1) * 2);
        if (food < 0) storedFood = food;
        else if (inProgressUnit != null && inProgressUnit.equals(UnitType.SETTLER)) storedFood = 0;
        else storedFood = food;
    }

    public void calculateStrength() {
        //TODO update strength based on the unit inside the city
    }
}
