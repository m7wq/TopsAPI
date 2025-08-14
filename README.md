# Installing

`-` [![](https://jitpack.io/v/m7wq/TopsAPI.svg)](https://jitpack.io/#m7wq/TopsAPI)

## Repository

```xml
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
```

```gradle
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			mavenCentral()
			maven { url 'https://jitpack.io' }
		}
```

## Dependencies

```xml
    <!-- 
        Replace VERSION with the API version
        Note: Minecraft version doesnt require the core
     -->
    
    <!-- Minecraft version of the API -->
	<dependency>
	    <groupId>com.github.m7wq</groupId>
	    <artifactId>TopsAPI-minecraft</artifactId>
	    <version>VERSION</version>
	</dependency>

    <!-- Normal Java version of the API -->
    <dependency>
	    <groupId>com.github.m7wq</groupId>
	    <artifactId>TopsAPI-core</artifactId>
	    <version>VERSION</version>
	</dependency>
```

```gradle
    /*
        Replace VERSION with the API version
        Note: Minecraft version doesnt require the core
    */

    
	dependencies {

            // Minecraft version of the API
	        implementation 'com.github.m7wq:TopsAPI-minecraft:VERSION'

            // Normal Java version of the API
            implementation 'com.github.m7wq:TopsAPI-core:VERSION'
	}
```

# Quick Guide

## Minecraft

`-` **Loading The API**
```java

    // in loading you have to insert your plugin
    // this -> represents a Plugin object
    TopsAPI.load(this);

    // NOTE: TopsAPI#load(plugin) returns an instance
    TopsAPI topsAPI = TopsAPI.load(this);

    // Or you can use getInstance() but this require loading the API
    TopsAPI topsAPI = TopsAPI.getInstance();
```

`-` **Editing Tops Config**
```java
        // Geting the Config
        TopsConfig topsConfig = TopsAPI.getInstance().getConfig();

        // Refresh Interval is the time between every refresh to the holograms (IN TICKS)
        // NOTE: 1 Second = 20 Ticks
        topsConfig.setRefreshInterval(60*20);

        // Sends Holograms spawning details to the console (if true)
        topsConfig.setDebug(false);
```

**Tops Config Default Values:**
```yml
DEBUG: false
REFRESH_INTERVAL: 200
```
`-` **Making a container and appending it**
```java
        HologramsContainer hologramsContainer;

        Map<Player, Data> dataMap = new HashMap<>(); // Your Data map
        
        // dataMap.put...

        // Initialize your container
        hologramsContainer = new HologramsContainer(
            HologramConfig.builder().build(), // Configuration of the holograms container
             new Location(null, 0, 0, 0) // Holograms main location
        );

        SortingProcessor<Player, Data> processor = SortingProcessor.<Player, Data>builder()
            .comparator(
                Comparator.comparingInt(entry -> entry.getValue().getKills()) // Comparing Method (by kills)
            )
            .mapper(entry -> Map.entry(entry.getKey().getName(), entry.getValue().getKills())) // Mapping into <String, Integer>
            .map(dataMap) // Tops map that is gonna be sorted
            .limit(10) // Tops limit (amount of players on the top leaderboard)
            .build();  
            


        TopsAPI.getInstance().appendContainer(hologramsContainer, processor);
```

`NOTE:` **When you append the cointainer it will automatically displays it in the specified location**

`-` **Editing Holograms Container Config**
```java
        HologramConfig config = hologramsContainer.getConfig();
        
        // The header of the holograms container
        config.setHeader("-----LEADERBOARD-----");

        /*
         * Line design
         * Placeholders:-
         * 1. %rank% the place of the player in the leaderboard
         * 2. %name% player-in-leaderboard name
         * 3. %amount% the amount of what the holograms container comparing by
         */
        config.setLineFormat("%rank%. %name% - %amount%");
        
        // The space between each hologram into the container
        config.setLineSpacing(0.245);

        /*
         * IN THE LATEST UPDATES: I added LuckPerms support
         * If lcukperms is found this will be automatically replaced with %name%
         * Placeholders:-
         * 1. %prefix% The prefix of the player rank
         * 2. %suffix% The suffix of the player rank
         * 3. %name% The name of the current player
         * NOTE:-
         * 1. If %prefix% was written on the format and player rank doesnt have a prefix the replacment will be ""
         * 2. If %suffix% was written on the format and player rank doesnt have a suffix the replacment will be ""
         * - Thats mean if %prefix% and %suffix% were written and player rank didnt have them the output will be "%name%" (the name only)
         */
        config.setLuckPermsFormat("%prefix% | %suffix%%name%");

        // The footer of the holograms container
        config.setFooter("----------------------");

        // Or just initialize in holograms container creating
        hologramsContainer = new HologramsContainer(

            // Un-defined will be its default value
            HologramConfig.builder()
                .footer("-------FOOTER--------")
                .header("-----------HEADER----").build(), 
            myLocation
        );
```

**Hologram Config Default Values:**
```yml
LUCKPERMS_FORMAT: "%prefix% %sufix%%name%"
LINE_SPACING: 0.25
HEADER: "&8-------- LEADERBOARD --------"
FOOTER: "&8-------- LEADERBOARD --------"
LINE_FORMAT: "&8#%rank%. &e%name% &7(&f%amount%&7)"
```

## Core

`-` **Compare Method**
```java

        // Make  your custom comparing method
        CompareMethod<String,Integer> compareMethod = (map,limit)->{
  
            return map.entrySet().stream()
                    .sorted(Map.Entry.<String,Integer>comparingByValue().reversed())
                    .limit(limit)
                    .collect(Collectors.toList());
        };

        // Built-in
        ComparingByValue comparingByValue = new ComparingByValue<>();
```

`-` **TOPS addon**
```java
        // The benefit is just a better displaying for beginners
        // And also if you do advanced comparing process or something likely
        Top<String,Integer> top = (map,limit)->{

     
            List<Map.Entry<String,Integer>> simpleSort;

            simpleSort = new ComparingByValue<String, Integer>().compare(map, limit);

            return simpleSort;


        };

        // Built In
        top = new NameByNumberTop();

        // Use it anywhere at anytime 
        top.getTop(map, 10);
```
