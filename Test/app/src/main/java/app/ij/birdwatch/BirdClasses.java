package app.ij.birdwatch;

import java.util.ArrayList;
import java.util.LinkedHashMap;

class BirdClasses {
    public static ArrayList<String> classes;
    public static LinkedHashMap<String, String> threat;

    void init() {
        classes = new ArrayList<>();
        threat = new LinkedHashMap<>();
        load();
    }

    void load() {
        classes.add("African Firefinch");
        threat.put("African Firefinch", "Least Concern");
        classes.add("Albatross");
        threat.put("Albatross", "Not Extinct");
        classes.add("Alexandrine Parakeet");
        threat.put("Alexandrine Parakeet", "Near Threatened");
        classes.add("American Avocet");
        threat.put("American Avocet", "Least Concern");
        classes.add("American Bittern");
        threat.put("American Bittern", "Least Concern");
        classes.add("American Coot");
        threat.put("American Coot", "Least Concern");
        classes.add("American Goldfinch");
        threat.put("American Goldfinch", "Least Concern");
        classes.add("American Kestrel");
        threat.put("American Kestrel", "Least Concern");
        classes.add("American Pipit");
        threat.put("American Pipit", "Least Concern");
        classes.add("American Redstart");
        threat.put("American Redstart", "Least Concern");
        classes.add("Anhinga");
        threat.put("Anhinga", "Least Concern");
        classes.add("Annas Hummingbird");
        threat.put("Annas Hummingbird", "Least Concern");
        classes.add("Antbird");
        threat.put("Antbird", "Critically Endangered");
        classes.add("Araripe Manakin");
        threat.put("Araripe Manakin", "Critically Endangered");
        classes.add("Asian Crested Ibis");
        threat.put("Asian Crested Ibis", "Endangered");
        classes.add("Bald Eagle");
        threat.put("Bald Eagle", "Least Concern");
        classes.add("Bali Starling");
        threat.put("Bali Starling", "Critically Endangered");
        classes.add("Baltimore Oriole");
        threat.put("Baltimore Oriole", "Least Concern");
        classes.add("Bananaquit");
        threat.put("Bananaquit", "Least Concern");
        classes.add("Bar-tailed Godwit");
        threat.put("Bar-tailed Godwit", "Least Concern");
        classes.add("Barn Owl");
        threat.put("Barn Owl", "Least Concern");
        classes.add("Barn Swallow");
        threat.put("Barn Swallow", "Least Concern");
        classes.add("Barred Puffbird");
        threat.put("Barred Puffbird", "Least Concern");
        classes.add("Bay-breasted Warbler");
        threat.put("Bay-breasted Warbler", "Least Concern");
        classes.add("Bearded Barbet");
        threat.put("Bearded Barbet", "Least Concern");
        classes.add("Belted Kingfisher");
        threat.put("Belted Kingfisher", "Least Concern");
        classes.add("Bird Of Paradise");
        threat.put("Bird Of Paradise", "Not Extinct");
        classes.add("Black Francolin");
        threat.put("Black Francolin", "Least Concern");
        classes.add("Black Skimmer");
        threat.put("Black Skimmer", "Least Concern");
        classes.add("Black Swan");
        threat.put("Black Swan", "Least Concern");
        classes.add("Black Throated Warbler");
        threat.put("Black Throated Warbler", "Least Concern");
        classes.add("Black Vulture");
        threat.put("Black Vulture", "Least Concern");
        classes.add("Black-capped Chickadee");
        threat.put("Black-capped Chickadee", "Least Concern");
        classes.add("Black-necked Grebe");
        threat.put("Black-necked Grebe", "Least Concern");
        classes.add("Black-throated Sparrow");
        threat.put("Black-throated Sparrow", "Least Concern");
        classes.add("Blackburniam Warbler");
        threat.put("Blackburniam Warbler", "Least Concern");
        classes.add("Blue Grouse");
        threat.put("Blue Grouse", "Least Concern");
        classes.add("Blue Heron");
        threat.put("Blue Heron", "Least Concern");
        classes.add("Bobolink");
        threat.put("Bobolink", "Near Threatened");
        classes.add("Brown Noody");
        threat.put("Brown Noody", "Least Concern");
        classes.add("Brown Thrasher");
        threat.put("Brown Thrasher", "Least Concern");
        classes.add("Cactus Wren");
        threat.put("Cactus Wren", "Least Concern");
        classes.add("California Condor");
        threat.put("California Condor", "Critically Endangered");
        classes.add("California Gull");
        threat.put("California Gull", "Least Concern");
        classes.add("California Quail");
        threat.put("California Quail", "Least Concern");
        classes.add("Canary");
        threat.put("Canary", "Least Concern");
        classes.add("Cape May Warbler");
        threat.put("Cape May Warbler", "Least Concern");
        classes.add("Capuchin Bird");
        threat.put("Capuchin Bird", "Critically Endangered");
        classes.add("Carmine Bee-eater");
        threat.put("Carmine Bee-eater", "Least Concern");
        classes.add("Caspian Tern");
        threat.put("Caspian Tern", "Least Concern");
        classes.add("Cassowary");
        threat.put("Cassowary", "Vulnerable");
        classes.add("Chara De Collar");
        threat.put("Chara De Collar", "Least Concern");
        classes.add("Chipping Sparrow");
        threat.put("Chipping Sparrow", "Least Concern");
        classes.add("Chukar Partridge");
        threat.put("Chukar Partridge", "Least Concern");
        classes.add("Cinnamon Teal");
        threat.put("Cinnamon Teal", "Least Concern");
        classes.add("Cock Of The Rock");
        threat.put("Cock Of The Rock", "Least Concern");
        classes.add("Cockatoo");
        threat.put("Cockatoo", "Not Extinct");
        classes.add("Common Grackle");
        threat.put("Common Grackle", "Least Concern");
        classes.add("Common House Martin");
        threat.put("Common House Martin", "Least Concern");
        classes.add("Common Loon");
        threat.put("Common Loon", "Least Concern");
        classes.add("Common Poorwill");
        threat.put("Common Poorwill", "Least Concern");
        classes.add("Common Starling");
        threat.put("Common Starling", "Least Concern");
        classes.add("Couchs Kingbird");
        threat.put("Couchs Kingbird", "Least Concern");
        classes.add("Crested Auklet");
        threat.put("Crested Auklet", "Least Concern");
        classes.add("Crested Caracara");
        threat.put("Crested Caracara", "Least Concern");
        classes.add("Crow");
        threat.put("Crow", "Not Extinct");
        classes.add("Crowned Pigeon");
        threat.put("Crowned Pigeon", "Not Extinct");
        classes.add("Cuban Tody");
        threat.put("Cuban Tody", "Least Concern");
        classes.add("Curl Crested Aracuri");
        threat.put("Curl Crested Aracuri", "Least Concern");
        classes.add("D-arnauds Barbet");
        threat.put("D-arnauds Barbet", "Least Concern");
        classes.add("Dark Eyed Junco");
        threat.put("Dark Eyed Junco", "Least Concern");
        classes.add("Downy Woodpecker");
        threat.put("Downy Woodpecker", "Least Concern");
        classes.add("Eastern Bluebird");
        threat.put("Eastern Bluebird", "Least Concern");
        classes.add("Eastern Meadowlark");
        threat.put("Eastern Meadowlark", "Least Concern");
        classes.add("Eastern Rosella");
        threat.put("Eastern Rosella", "Least Concern");
        classes.add("Eastern Towee");
        threat.put("Eastern Towee", "Near Threatened");
        classes.add("Elegant Trogon");
        threat.put("Elegant Trogon", "Least Concern");
        classes.add("Elliots Pheasant");
        threat.put("Elliots Pheasant", "Near Threatened");
        classes.add("Emperor Penguin");
        threat.put("Emperor Penguin", "Near Threatened");
        classes.add("Emu");
        threat.put("Emu", "Least Concern");
        classes.add("Eurasian Magpie");
        threat.put("Eurasian Magpie", "Least Concern");
        classes.add("Evening Grosbeak");
        threat.put("Evening Grosbeak", "Least Concern");
        classes.add("Flame Tanager");
        threat.put("Flame Tanager", "Least Concern");
        classes.add("Flamingo");
        threat.put("Flamingo", "Near Threatened");
        classes.add("Frigate");
        threat.put("Frigate", "Critically Endangered");
        classes.add("Gambels Quail");
        threat.put("Gambels Quail", "Least Concern");
        classes.add("Gila Woodpecker");
        threat.put("Gila Woodpecker", "Least Concern");
        classes.add("Gilded Flicker");
        threat.put("Gilded Flicker", "Least Concern");
        classes.add("Glossy Ibis");
        threat.put("Glossy Ibis", "Least Concern");
        classes.add("Gold Wing Warbler");
        threat.put("Gold Wing Warbler", "Near Threatened");
        classes.add("Golden Cheeked Warbler");
        threat.put("Golden Cheeked Warbler", "Endangered");
        classes.add("Golden Chlorophonia");
        threat.put("Golden Chlorophonia", "Least Concern");
        classes.add("Golden Eagle");
        threat.put("Golden Eagle", "Least Concern");
        classes.add("Golden Pheasant");
        threat.put("Golden Pheasant", "Least Concern");
        classes.add("Golden Pipit");
        threat.put("Golden Pipit", "Least Concern");
        classes.add("Gouldian Finch");
        threat.put("Gouldian Finch", "Near Threatened");
        classes.add("Gray Catbird");
        threat.put("Gray Catbird", "Least Concern");
        classes.add("Gray Partridge");
        threat.put("Gray Partridge", "Least Concern");
        classes.add("Green Jay");
        threat.put("Green Jay", "Least Concern");
        classes.add("Grey Plover");
        threat.put("Grey Plover", "Least Concern");
        classes.add("Guineafowl");
        threat.put("Guineafowl", "Not Extinct");
        classes.add("Gyrfalcon");
        threat.put("Gyrfalcon", "Least Concern");
        classes.add("Harpy Eagle");
        threat.put("Harpy Eagle", "Near Threatened");
        classes.add("Hawaiian Goose");
        threat.put("Hawaiian Goose", "Vulnerable");
        classes.add("Hooded Merganser");
        threat.put("Hooded Merganser", "Least Concern");
        classes.add("Hoopoes");
        threat.put("Hoopoes", "Not Extinct");
        classes.add("Hornbill");
        threat.put("Hornbill", "Not Extinct");
        classes.add("Horned Guan");
        threat.put("Horned Guan", "Endangered");
        classes.add("Horned Sungem");
        threat.put("Horned Sungem", "Endangered");
        classes.add("House Finch");
        threat.put("House Finch", "Least Concern");
        classes.add("House Sparrow");
        threat.put("House Sparrow", "Least Concern");
        classes.add("Imperial Shag");
        threat.put("Imperial Shag", "Least Concern");
        classes.add("Inca Tern");
        threat.put("Inca Tern", "Near Threatened");
        classes.add("Indian Bustard");
        threat.put("Indian Bustard", "Critically Endangered");
        classes.add("Indigo Bunting");
        threat.put("Indigo Bunting", "Least Concern");
        classes.add("Jabiru");
        threat.put("Jabiru", "Near Threatened");
        classes.add("Javan Magpie");
        threat.put("Javan Magpie", "Critically Endangered");
        classes.add("Kakapo");
        threat.put("Kakapo", "Critically Endangered");
        classes.add("Killdear");
        threat.put("Killdear", "Least Concern");
        classes.add("King Vulture");
        threat.put("King Vulture", "Least Concern");
        classes.add("Kiwi");
        threat.put("Kiwi", "Not Extinct");
        classes.add("Kookaburra");
        threat.put("Kookaburra", "Least Concern");
        classes.add("Lark Bunting");
        threat.put("Lark Bunting", "Least Concern");
        classes.add("Lears Macaw");
        threat.put("Lears Macaw", "Endangered");
        classes.add("Lilac Roller");
        threat.put("Lilac Roller", "Least Concern");
        classes.add("Long-eared Owl");
        threat.put("Long-eared Owl", "Least Concern");
        classes.add("Malabar Hornbill");
        threat.put("Malabar Hornbill", "Least Concern");
        classes.add("Malachite Kingfisher");
        threat.put("Malachite Kingfisher", "Least Concern");
        classes.add("Maleo");
        threat.put("Maleo", "Endangered");
        classes.add("Mallard Duck");
        threat.put("Mallard Duck", "Least Concern");
        classes.add("Mandrin Duck");
        threat.put("Mandrin Duck", "Least Concern");
        classes.add("Marabou Stork");
        threat.put("Marabou Stork", "Not Extinct");
        classes.add("Masked Booby");
        threat.put("Masked Booby", "Least Concern");
        classes.add("Mikado Pheasant");
        threat.put("Mikado Pheasant", "Near Threatened");
        classes.add("Mourning Dove");
        threat.put("Mourning Dove", "Least Concern");
        classes.add("Myna");
        threat.put("Myna", "Least Concern");
        classes.add("Nicobar Pigeon");
        threat.put("Nicobar Pigeon", "Critically Endangered");
        classes.add("Northern Cardinal");
        threat.put("Northern Cardinal", "Least Concern");
        classes.add("Northern Flicker");
        threat.put("Northern Flicker", "Least Concern");
        classes.add("Northern Gannet");
        threat.put("Northern Gannet", "Least Concern");
        classes.add("Northern Goshawk");
        threat.put("Northern Goshawk", "Least Concern");
        classes.add("Northern Jacana");
        threat.put("Northern Jacana", "Least Concern");
        classes.add("Northern Mockingbird");
        threat.put("Northern Mockingbird", "Least Concern");
        classes.add("Northern Parula");
        threat.put("Northern Parula", "Least Concern");
        classes.add("Northern Red Bishop");
        threat.put("Northern Red Bishop", "Least Concern");
        classes.add("Ocellated Turkey");
        threat.put("Ocellated Turkey", "Near Threatened");
        classes.add("Okinawa Rail");
        threat.put("Okinawa Rail", "Not Extinct");
        classes.add("Osprey");
        threat.put("Osprey", "Least Concern");
        classes.add("Ostrich");
        threat.put("Ostrich", "Least Concern");
        classes.add("Painted Buntig");
        threat.put("Painted Buntig", "Near Threatened");
        classes.add("Palila");
        threat.put("Palila", "Critically Endangered");
        classes.add("Paradise Tanager");
        threat.put("Paradise Tanager", "Least Concern");
        classes.add("Parus Major");
        threat.put("Parus Major", "Least Concern");
        classes.add("Peacock");
        threat.put("Peacock", "Least Concern");
        classes.add("Pelican");
        threat.put("Pelican", "Not Extinct");
        classes.add("Peregrine Falcon");
        threat.put("Peregrine Falcon", "Least Concern");
        classes.add("Philippine Eagle");
        threat.put("Philippine Eagle", "Critically Endangered");
        classes.add("Pink Robin");
        threat.put("Pink Robin", "Least Concern");
        classes.add("Puffin");
        threat.put("Puffin", "Not Extinct");
        classes.add("Purple Finch");
        threat.put("Purple Finch", "Not Extinct");
        classes.add("Purple Gallinule");
        threat.put("Purple Gallinule", "Least Concern");
        classes.add("Purple Martin");
        threat.put("Purple Martin", "Least Concern");
        classes.add("Purple Swamphen");
        threat.put("Purple Swamphen", "Least Concern");
        classes.add("Quetzal");
        threat.put("Quetzal", "Near Threatened");
        classes.add("Rainbow Lorikeet");
        threat.put("Rainbow Lorikeet", "Least Concern");
        classes.add("Razorbill");
        threat.put("Razorbill", "Least Concern");
        classes.add("Red Faced Cormorant");
        threat.put("Red Faced Cormorant", "Least Concern");
        classes.add("Red Faced Warbler");
        threat.put("Red Faced Warbler", "Least Concern");
        classes.add("Red Headed Duck");
        threat.put("Red Headed Duck", "Critically Endangered");
        classes.add("Red Headed Woodpecker");
        threat.put("Red Headed Woodpecker", "Near Threatened");
        classes.add("Red Honey Creeper");
        threat.put("Red Honey Creeper", "Least Concern");
        classes.add("Red Throated Bee Eater");
        threat.put("Red Throated Bee Eater", "Least Concern");
        classes.add("Red Winged Blackbird");
        threat.put("Red Winged Blackbird", "Not Extinct");
        classes.add("Red Wiskered Bulbul");
        threat.put("Red Wiskered Bulbul", "Least Concern");
        classes.add("Ring-necked Pheasant");
        threat.put("Ring-necked Pheasant", "Least Concern");
        classes.add("Roadrunner");
        threat.put("Roadrunner", "Least Concern");
        classes.add("Robin");
        threat.put("Robin", "Least Concern");
        classes.add("Rock Dove");
        threat.put("Rock Dove", "Least Concern");
        classes.add("Rosy Faced Lovebird");
        threat.put("Rosy Faced Lovebird", "Least Concern");
        classes.add("Rough Leg Buzzard");
        threat.put("Rough Leg Buzzard", "Least Concern");
        classes.add("Ruby Throated Hummingbird");
        threat.put("Ruby Throated Hummingbird", "Least Concern");
        classes.add("Rufous Kingfisher");
        threat.put("Rufous Kingfisher", "Near Threatened");
        classes.add("Rufuos Motmot");
        threat.put("Rufuos Motmot", "Least Concern");
        classes.add("Sand Martin");
        threat.put("Sand Martin", "Least Concern");
        classes.add("Scarlet Ibis");
        threat.put("Scarlet Ibis", "Least Concern");
        classes.add("Scarlet Macaw");
        threat.put("Scarlet Macaw", "Least Concern");
        classes.add("Shoebill");
        threat.put("Shoebill", "Critically Endangered");
        classes.add("Smiths Longspur");
        threat.put("Smiths Longspur", "Least Concern");
        classes.add("Snowy Egret");
        threat.put("Snowy Egret", "Least Concern");
        classes.add("Snowy Owl");
        threat.put("Snowy Owl", "Least Concern");
        classes.add("Sora");
        threat.put("Sora", "Least Concern");
        classes.add("Spangled Cotinga");
        threat.put("Spangled Cotinga", "Least Concern");
        classes.add("Splendid Wren");
        threat.put("Splendid Wren", "Least Concern");
        classes.add("Spoon Billed Sandpiper");
        threat.put("Spoon Billed Sandpiper", "Critically Endangered");
        classes.add("Spoonbill");
        threat.put("Spoonbill", "Not Extinct");
        classes.add("Steamer Duck");
        threat.put("Steamer Duck", "Least Concern");
        classes.add("Stork Billed Kingfisher");
        threat.put("Stork Billed Kingfisher", "Least Concern");
        classes.add("Strawberry Finch");
        threat.put("Strawberry Finch", "Least Concern");
        classes.add("Stripped Swallow");
        threat.put("Stripped Swallow", "Least Concern");
        classes.add("Superb Starling");
        threat.put("Superb Starling", "Least Concern");
        classes.add("Taiwan Magpie");
        threat.put("Taiwan Magpie", "Least Concern");
        classes.add("Takahe");
        threat.put("Takahe", "Endangered");
        classes.add("Tasmanian Hen");
        threat.put("Tasmanian Hen", "Least Concern");
        classes.add("Teal Duck");
        threat.put("Teal Duck", "Least Concern");
        classes.add("Tit Mouse");
        threat.put("Tit Mouse", "Not Extinct");
        classes.add("Touchan");
        threat.put("Touchan", "Least Concern");
        classes.add("Townsends Warbler");
        threat.put("Townsends Warbler", "Least Concern");
        classes.add("Tree Swallow");
        threat.put("Tree Swallow", "Not Extinct");
        classes.add("Trumpter Swan");
        threat.put("Trumpter Swan", "Least Concern");
        classes.add("Turkey Vulture");
        threat.put("Turkey Vulture", "Least Concern");
        classes.add("Turquoise Motmot");
        threat.put("Turquoise Motmot", "Least Concern");
        classes.add("Varied Thrush");
        threat.put("Varied Thrush", "Least Concern");
        classes.add("Venezuelian Troupial");
        threat.put("Venezuelian Troupial", "Least Concern");
        classes.add("Vermilion Flycather");
        threat.put("Vermilion Flycather", "Least Concern");
        classes.add("Violet Green Swallow");
        threat.put("Violet Green Swallow", "Least Concern");
        classes.add("Wattled Curassow");
        threat.put("Wattled Curassow", "Endangered");
        classes.add("Whimbrel");
        threat.put("Whimbrel", "Least Concern");
        classes.add("White Cheeked Turaco");
        threat.put("White Cheeked Turaco", "Least Concern");
        classes.add("White Necked Raven");
        threat.put("White Necked Raven", "Least Concern");
        classes.add("White Tailed Tropic");
        threat.put("White Tailed Tropic", "Least Concern");
        classes.add("Wild Turkey");
        threat.put("Wild Turkey", "Least Concern");
        classes.add("Wilsons Bird Of Paradise");
        threat.put("Wilsons Bird Of Paradise", "Near Threatened");
        classes.add("Wood Duck");
        threat.put("Wood Duck", "Least Concern");
        classes.add("Yellow Cacique");
        threat.put("Yellow Cacique", "Least Concern");
        classes.add("Yellow Headed Blackbird");
        threat.put("Yellow Headed Blackbird", "Least Concern");
    }

}