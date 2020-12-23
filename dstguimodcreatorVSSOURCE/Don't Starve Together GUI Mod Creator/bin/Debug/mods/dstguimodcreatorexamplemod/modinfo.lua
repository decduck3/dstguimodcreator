-- This information tells other players more about the mod
name = "Balanced Warly"
description = "Warly now works as a character!"
author = "decduck"
version = "1.8"

forumthread = ""

-- This lets other players know if your mod is out of date, update it to match the current version in the game
api_version = 10

--This let's the game know that this mod doesn't need to be listed in the server's mod listing
client_only_mod = false

--Let the mod system know that this mod is functional with Don't Starve Together
dst_compatible = true

all_clients_require_mod = true

icon = "modicon.tex" --preview image
icon_atlas = "modicon.xml"

configuration_options = {
    {
        name = "FOODMEMORY",
        label = "Food Memory",
        hover = "Control the effectiveness of food memory",
        options = {
            {description="Default",data={ 0.95, 0.9, 0.86, 0.8, 0.75 }},
            {description="Easier",data={ 1, 0.95, 0.9, 0.85, 0.7 }},
            {description="Harder",data={ 0.85, 0.7, 0.65, 0.5, 0.42 }},
        },
        default = 0,
    },
    {
        name = "FOODMEMORYLENGTH",
        label = "Food Memory Length",
        hover = "Control the length of food memory",
        options = {
            {description="Shorter",data=0.5},
            {description="Default",data=1},
            {description="Longer",data=1.5},
        },
        default = 0,
    },
    {
        name = "CANEAT",
        label = "Can eat normal food?",
        hover = "Control whether or not Warly can eat normal food.",
        options = {
            {description="Yes",data=0},
            {description="No",data=1},
        },
        default = 0,
    },
    {
        name = "BETTERFOOD",
        label = "Better stats for Warly food?",
        hover = "Control if Warly's food is better than usual",
        options = {
            {description="Yes",data=0},
            {description="No",data=1},
        },
        default = 0,
    },
    {
        name = "BETTERABFOOD",
        label = "Better abilities for Warly food?",
        hover = "Control if Warly's food's abilites is better than usual",
        options = {
            {description="Yes",data=0},
            {description="No",data=1},
        },
        default = 0,
    },
    {
        name = "RECIPES",
        label = "Recipes for Warly's things",
        hover = "Control if certain things are cheaper or more expensive",
        options = {
            {description="Default",data=0},
            {description="More Expensive",data=1},
            {description="Less Expensive",data=2},
        },
        default = 0,
    },
}