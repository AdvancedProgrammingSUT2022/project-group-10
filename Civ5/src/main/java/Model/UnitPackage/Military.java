package Model.UnitPackage;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class Military extends Unit {

    private int combatStrength;
    private int rangedCombatStrength;
    private int range;
    private boolean isReady; //hame ready an joz siege//ke faghat if(isReady)
    private int XP;

    public Military(UnitType militaryType) {
        super(militaryType);
        this.combatStrength = militaryType.getCombatStrength();
        this.rangedCombatStrength = militaryType.getRangedCombatStrength();
        this.range = militaryType.getRange();
        this.isReady = !isSiege();
    }

    @Override
    public void setStatus(String string) {
        super.setStatus(string);
        if (string.equals("alert")) this.status = UnitStatus.ALERT;
        else if (string.equals("fortify")) this.status = UnitStatus.FORTIFY;
        else if (string.equals("garrison")) this.status = UnitStatus.FORTIFY;
        else if (string.equals("setup ranged")) this.status = UnitStatus.SIEGEPREP;
    }

    @Override
    public void kill() {
        tile.setMilitary(null);
        civilization.getUnits().remove(this);
    }

    public void setReady(boolean ready) {
        isReady = ready;
    }

    public void upgrade() {
        //TODO... change type (costs gold)
    }


}
