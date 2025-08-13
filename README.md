# Installing

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

`-` **Editing TopsConfig**

```java
        // Geting the Config
        TopsConfig topsConfig = TopsAPI.getInstance().getConfig();

        // The header of the holograms container
        topsConfig.setHeader("-----LEADERBOARD-----");

        /*
         * Line design
         * Placeholders:-
         * 1. %rank% the place of the player in the leaderboard
         * 2. %name% player-in-leaderboard name
         * 3. %amount% the amount of what the holograms container comparing by
        */
        topsConfig.setLineFormat("#%rank%. %name% with %amount%");

        // The space between each hologram into the container
        topsConfig.setLineSpacing(0.245);

        // Refresh Interval is the time between every refresh to the holograms (IN TICKS)
        // NOTE: 1 Second = 20 Ticks
        topsConfig.setRefreshInterval(60*20);

        // THe footer of the holograms container
        topsConfig.setFooter("-----LEADERBOARD------");
```

**Default Values:**
```yml
HEADER: "&8-------- LEADERBOARD --------"
LINE_FORMAT: "&8#%rank%. &e%name% &7(&f%amount%&7)"
FOOTER: "&8-------- LEADERBOARD --------"
LINE_SPACING: 0.25
REFRESH_INTERVAL: 200
```

`-` **Making a container and appendingit**
```java
        HologramsContainer<Player, Data> hologramsContainer;

        Map<Player, Data> dataMap = new HashMap<>(); // Your Data map
        
        // dataMap.put...

        // Initialize your container
        hologramsContainer = new HologramsContainer<>(

             new Location(null, 0, 0, 0), // Holograms main location

             Comparator.comparingInt(entry -> entry.getValue().getKills()),  // Comparing Method (by kills)

             entry -> Map.entry(entry.getKey().getName(), entry.getValue().getKills()), // Mapping into <String, Integer>

             dataMap, // Tops map

             10 // Tops limit (amount of players on the top leaderboard)

        );

        TopsAPI.getInstance().appendContainer(hologramsContainer);
```

`NOTE:` **When you append the cointainer it will automatically displays it in the specified location**


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