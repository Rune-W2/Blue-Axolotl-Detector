# Blue-Axolotl-Detector
Simple 1.20.1 mod that adds a /bad (blue axolotl detector) command into the game that scans for any blue axolotl's around the player and will send a private message to the player if it detects a blue axolotl.

## Installation
1. Download and run the [Fabric installer](https://fabricmc.net/use).
   - Select 1.20.1 from the versions list.
1. Download the [Fabric API](https://minecraft.curseforge.com/projects/fabric)
  for version 1.20.1 and move it to the mods folder (`C:\Users\<User>\AppData\Roaming\.minecraft\mods`). Create a mods folder if there isn't one.
1. Download Blue-Axolotl-Detector from the [releases page](https://github.com/Rune-W2/Blue-Axolotl-Detector/releases)
   and move it to the mods folder (`C:\Users\<User>\AppData\Roaming\.minecraft\mods`).

## Building from source
1. Clone the repository
   ```
   git clone https://github.com/Rune-W2/Blue-Axolotl-Detector.git
   cd Blue-Axolotl-Detector
   ```
1. Build the .jar file
   ```
   gradlew build
   ```
   - Note: on Windows, use `gradlew` rather than `./gradlew`.
  
File will be in /build/libs/

## Usage 

This mod implements a /bad (blueaxolotldetector) command to the game that scans for a blue axolotl around the player. The /bad command also works on servers too for all releases.

## Contributing
1. Clone the repository
   ```
   git clone git clone https://github.com/Rune-W2/Blue-Axolotl-Detector.git
   cd Blue-Axolotl-Detector
   ```
1. Generate the Minecraft source code
   ```
   ./gradlew genSources
   ```
   - Note: on Windows, use `gradlew` rather than `./gradlew`.
1. Import the project into your preferred IDE.
   1. If you use IntelliJ (the preferred option), you can simply import the project as a Gradle project.
   1. If you use Eclipse, you need to `./gradlew eclipse` before importing the project as an Eclipse project.
1. Edit the code
1. After testing in the IDE, build a JAR to test whether it works outside the IDE too
   ```
   ./gradlew build
   ```
   The mod JAR may be found in the `build/libs` directory
1. [Create a pull request](https://help.github.com/en/articles/creating-a-pull-request)
   so that your changes can be integrated into Blue-Axolotl-Detector
   - Note: for large contributions, create an issue before doing all that
     work, to ask whether your pull request is likely to be accepted
