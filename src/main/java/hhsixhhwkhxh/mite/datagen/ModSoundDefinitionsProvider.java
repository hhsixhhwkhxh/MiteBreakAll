package hhsixhhwkhxh.mite.datagen;

import hhsixhhwkhxh.mite.MiteBreakAll;
import hhsixhhwkhxh.mite.sound.ModSoundEvents;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.data.SoundDefinition;
import net.neoforged.neoforge.common.data.SoundDefinitionsProvider;

public class ModSoundDefinitionsProvider extends SoundDefinitionsProvider {

    public ModSoundDefinitionsProvider(PackOutput output) {
        super(output, MiteBreakAll.MOD_ID);
    }

    @Override
    public void registerSounds() {
        add(ModSoundEvents.ELEPHANT_DEATH, SoundDefinition.definition()
                .with(
                        sound(modRL("elephant/death"))
                )
                .subtitle("subtitles.entity.elephant.death")
        );

        add(ModSoundEvents.ELEPHANT_HURT, SoundDefinition.definition()
                .with(
                        sound(modRL("elephant/hurt1")),
                        sound(modRL("elephant/hurt2"))
                )
                .subtitle("subtitles.entity.elephant.hurt")
        );

        add(ModSoundEvents.ELEPHANT_IDLE, SoundDefinition.definition()
                .with(
                        sound(modRL("elephant/idle1")),
                        sound(modRL("elephant/idle2")),
                        sound(modRL("elephant/idle3")),
                        sound(modRL("elephant/idle4")),
                        sound(modRL("elephant/idle5")),
                        sound(modRL("elephant/idle6")),
                        sound(modRL("elephant/idle7")),
                        sound(modRL("elephant/idle8"))
                        )
                .subtitle("subtitles.entity.elephant.idle")
        );

        add(ModSoundEvents.ELEPHANT_BABY_IDLE, SoundDefinition.definition()
                .with(
                        sound(modRL("elephant/baby_idle1")),
                        sound(modRL("elephant/baby_idle2"))
                )
                .subtitle("subtitles.entity.elephant.idle")
        );

        add(ModSoundEvents.COUGAR_AMBIENT, SoundDefinition.definition()
                .with(
                        sound(modRL("cougar/ambient1")),
                        sound(modRL("cougar/ambient2")),
                        sound(modRL("cougar/ambient3"))
                )
                .subtitle("subtitles.entity.cougar.ambient")
        );

        add(ModSoundEvents.COUGAR_ATTACK, SoundDefinition.definition()
                .with(
                        sound(modRL("cougar/attack"))
                )
                .subtitle("subtitles.entity.cougar.attack")
        );

        add(ModSoundEvents.COUGAR_DEATH, SoundDefinition.definition()
                .with(
                        sound(modRL("cougar/death"))
                )
                .subtitle("subtitles.entity.cougar.death")
        );

        add(ModSoundEvents.COUGAR_HURT, SoundDefinition.definition()
                .with(
                        sound(modRL("cougar/hurt1")),
                        sound(modRL("cougar/hurt2"))
                )
                .subtitle("subtitles.entity.cougar.hurt")
        );
    }

    private static ResourceLocation modRL(String path){
        return ResourceLocation.fromNamespaceAndPath(MiteBreakAll.MOD_ID,path);
    }
}
