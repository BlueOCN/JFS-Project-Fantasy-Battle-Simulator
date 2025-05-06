public class Archer extends Character{

    // Character description
    private static final String description = "Archer (Ranged Combat & Precision Strikes)";

    // Boosts precision for long-range shots
    private int accuracy;

    // Enhances critical hit chance and bow handling
    private int focus;

    // Reduces visibility to enemies, making ambushes more effective
    private int stealth;

    // Grants the ability to fire multiple arrows at once
    private int multiShot;

    // Enhances dodge rate against incoming attacks
    private int evasion;


    // Fires an arrow that ignores armor and deals bonus damage.
    public void abilityPiercingShot(Object enemy) {}

    // Teleports a short distance, evading attacks.
    public void abilityShadowStep() {}

    // Shoots a barrage of arrows that detonate on impact.
    public void abilityExplosiveVolley(Object enemy) {}

    // Temporarily increases accuracy and critical hit chance.
    public void abilityHawkEye() {}

    // Poisons enemies, dealing damage over time.
    public void abilityVenomShot(Object enemy) {}

}
