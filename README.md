# AutoBlock Plugin

AutoBlock is a Minecraft plugin that automatically converts specified items into their block form when a player collects a certain amount. For example, it can convert 9 diamonds into 1 diamond block when a player picks up the 9th diamond.

## Features

- Automatically converts items into block form based on configurable rules.
- Supports custom conversion rules via configuration file.
- Provides a command to reload the configuration without restarting the server.
- Permission-based command access.

## Commands

- `/abk reload` - Reloads the plugin's configuration.
    - Permission: `autoblock.reload`

## Configuration

The configuration file allows you to define custom conversion rules. The format is:

### `config.yml`
## RU
```yaml
# Конфигурационный файл для AutoBlock плагина
# Формат: <ключ>: <добываемый предмет>: <результирующий предмет>: <необходимое количество>: <количество результата>

# Пример для алмазов:
# При добыче алмазной руды (diamond_ore) будет проверяться инвентарь на наличие 9 алмазов (DIAMOND).
# Если алмазов достаточно, они будут преобразованы в 1 алмазный блок (DIAMOND_BLOCK).

diamond_ore: DIAMOND:DIAMOND_BLOCK:9:1

# Пример для золота:
# При добыче золотой руды (gold_ore) будет проверяться инвентарь на наличие 9 золотых слитков (GOLD_INGOT).
# Если слитков достаточно, они будут преобразованы в 1 золотой блок (GOLD_BLOCK).

gold_ore: GOLD_ORE:GOLD_BLOCK:9:1

# Пример для железа:
# При добыче железной руды (iron_ore) будет проверяться инвентарь на наличие 9 железных слитков (IRON_INGOT).
# Если слитков достаточно, они будут преобразованы в 1 железный блок (IRON_BLOCK).

iron_ore: GOLD_ORE:IRON_BLOCK:9:1
```
## EN
```yaml
# Configuration file for the AutoBlock plugin
# Format: <key>: <source item>: <result item>: <required amount>: <result amount>

# Example for diamonds:
# When mining diamond ore (diamond_ore), the inventory will be checked for 9 diamonds (DIAMOND).
# If there are enough diamonds, they will be converted into 1 diamond block (DIAMOND_BLOCK).

diamond_ore: DIAMOND:DIAMOND_BLOCK:9:1

# Example for gold:
# When mining gold ore (gold_ore), the inventory will be checked for 9 gold ingots (GOLD_INGOT).
# If there are enough ingots, they will be converted into 1 gold block (GOLD_BLOCK).

gold_ore: GOLD_ORE:GOLD_BLOCK:9:1

# Example for iron:
# When mining iron ore (iron_ore), the inventory will be checked for 9 iron ingots (IRON_INGOT).
# If there are enough ingots, they will be converted into 1 iron block (IRON_BLOCK).

iron_ore: GOLD_ORE:IRON_BLOCK:9:1

```

## Installation

1. Download the plugin jar file.
2. Place the jar file in your server's `plugins` directory.
3. Start your Minecraft server to generate the default configuration file.
4. Modify the `config.yml` file in the `plugins/AutoBlock` directory to your liking.
5. Use the `/abk reload` command to reload the configuration without restarting the server.