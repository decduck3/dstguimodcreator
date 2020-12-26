package export;

public class ExportTemplates {
    public static String modinfoTemplate = "name = {0}\n" +
            "version = {1}\n" +
            "description = {2}\n" +
            "author = {3}\n" +
            "\n" +
            "api_version = 10\n" +
            "\n" +
            "dst_compatible = true\n" +
            "\n" +
            "dont_starve_compatible = false\n" +
            "reign_of_giants_compatible = false\n" +
            "shipwrecked_compatible = false\n" +
            "\n" +
            "all_clients_require_mod = true\n";

    public static String modmainTemplate = "local prefabs = {\n" +
            "\t{0}\n" +
            "}\n" +
            "Assets = {\n" +
            "\t{1}\n" +
            "}";

    public static String itemTemplate = "local assets =\n" +
            "{\n" +
            "\t{0}\n" +
            "}\n" +
            "\n" +
            "prefabs = {\n" +
            "    {1}\n" +
            "}\n" +
            "\n" +
            "{2}\n" +
            "\n" +
            "local function init()\n" +
            "\tlocal inst = CreateEntity()\n" +
            "\n" +
            "\tinst.entity:AddTransform()\n" +
            "\tinst.entity:AddAnimState()\n" +
            "    inst.entity:AddSoundEmitter()\n" +
            "    inst.entity:AddNetwork()\n" +
            "\n" +
            "    MakeInventoryPhysics(inst)\n" +
            "\n" +
            "    inst.AnimState:SetBank({3})\n" +
            "    inst.AnimState:SetBuild({3})\n" +
            "    inst.AnimState:PlayAnimation(\"idle\")\n" +
            "\n" +
            "    if not TheWorld.ismastersim then\n" +
            "        return inst\n" +
            "    end\n" +
            "\n" +
            "    inst.entity:SetPristine()\n" +
            "    \n" +
            "    {7}\n" +
            "    inst:AddComponent(\"inventoryitem\")\n" +
            "    inst.components.inventoryitem.atlasname = {4}\n" +
            "    inst.components.inventoryitem.imagename = {5}\n" +
            "\n" +
            "    MakeHauntableLaunch(inst)\n" +
            "\n" +
            "    return inst\n" +
            "end\n" +
            "return  Prefab({6}, init, assets, prefabs)";

    //Component templates
    public static String anim = "\tAsset(\"ANIM\", \"REPLACE\"),\n{0}";
    public static String image = "\tAsset(\"IMAGE\", \"REPLACE\" ),\n{0}";
    public static String atlas = "\tAsset(\"ATLAS\", \"REPLACE\"),\n{0}";

    public static String armor = "    inst:AddComponent(\"armor\")\n" +
            "    inst.components.armor:InitCondition(ARMOR, ABSORPTION)\n{7}";
    public static String axe = "    inst:AddComponent(\"tool\")\n" +
            "    inst.components.tool:SetAction(ACTIONS.CHOP, SPEED)\n{7}";
    public static String equipable = "inst:AddComponent(\"equippable\")\n" +
            "    inst.components.equippable.equipslot = REPLACE\n{7}";

    public static String dapperness = "        inst.components.equippable.dapperness = REPLACE\n{7}";

    public static String chest = "EQUIPSLOTS.BODY";
    public static String hand = "EQUIPSLOTS.HEAD";

    public static String duriability = "        inst:AddComponent(\"finiteuses\")\n" +
            "        inst.components.finiteuses:SetMaxUses(TUNING.AXE_USES)\n" +
            "        inst.components.finiteuses:SetUses(TUNING.AXE_USES)\n" +
            "        inst.components.finiteuses:SetOnFinished(inst.Remove)\n" +
            "        inst.components.finiteuses:SetConsumption(ACTIONS.CHOP, 1)\n{7}";
}
