package com.hlysine.create_connected;

import com.hlysine.create_connected.compat.Mods;
import com.hlysine.create_connected.config.CStress;
import com.hlysine.create_connected.config.FeatureCategory;
import com.hlysine.create_connected.config.FeatureToggle;
import com.hlysine.create_connected.content.WrenchableBlock;
import com.hlysine.create_connected.content.brake.BrakeBlock;
import com.hlysine.create_connected.content.brassgearbox.BrassGearboxBlock;
import com.hlysine.create_connected.content.centrifugalclutch.CentrifugalClutchBlock;
import com.hlysine.create_connected.content.chaincogwheel.ChainCogwheelBlock;
import com.hlysine.create_connected.content.copycat.beam.CopycatBeamBlock;
import com.hlysine.create_connected.content.copycat.beam.CopycatBeamModel;
import com.hlysine.create_connected.content.copycat.block.CopycatBlockBlock;
import com.hlysine.create_connected.content.copycat.block.CopycatBlockModel;
import com.hlysine.create_connected.content.copycat.board.CopycatBoardBlock;
import com.hlysine.create_connected.content.copycat.board.CopycatBoardModel;
import com.hlysine.create_connected.content.copycat.fence.CopycatFenceBlock;
import com.hlysine.create_connected.content.copycat.fence.CopycatFenceModel;
import com.hlysine.create_connected.content.copycat.fence.WrappedFenceBlock;
import com.hlysine.create_connected.content.copycat.fencegate.CopycatFenceGateBlock;
import com.hlysine.create_connected.content.copycat.fencegate.CopycatFenceGateModel;
import com.hlysine.create_connected.content.copycat.fencegate.WrappedFenceGateBlock;
import com.hlysine.create_connected.content.copycat.slab.CopycatSlabBlock;
import com.hlysine.create_connected.content.copycat.slab.CopycatSlabModel;
import com.hlysine.create_connected.content.copycat.stairs.CopycatStairsBlock;
import com.hlysine.create_connected.content.copycat.stairs.CopycatStairsModel;
import com.hlysine.create_connected.content.copycat.stairs.WrappedStairsBlock;
import com.hlysine.create_connected.content.copycat.verticalstep.CopycatVerticalStepBlock;
import com.hlysine.create_connected.content.copycat.verticalstep.CopycatVerticalStepModel;
import com.hlysine.create_connected.content.copycat.wall.CopycatWallBlock;
import com.hlysine.create_connected.content.copycat.wall.CopycatWallModel;
import com.hlysine.create_connected.content.copycat.wall.WrappedWallBlock;
import com.hlysine.create_connected.content.crankwheel.CrankWheelBlock;
import com.hlysine.create_connected.content.crossconnector.CrossConnectorBlock;
import com.hlysine.create_connected.content.crossconnector.EncasedCrossConnectorBlock;
import com.hlysine.create_connected.content.fluidvessel.FluidVesselBlock;
import com.hlysine.create_connected.content.fluidvessel.FluidVesselGenerator;
import com.hlysine.create_connected.content.fluidvessel.FluidVesselItem;
import com.hlysine.create_connected.content.fluidvessel.FluidVesselModel;
import com.hlysine.create_connected.content.freewheelclutch.FreewheelClutchBlock;
import com.hlysine.create_connected.content.inventoryaccessport.InventoryAccessPortBlock;
import com.hlysine.create_connected.content.inventoryaccessport.InventoryAccessPortGenerator;
import com.hlysine.create_connected.content.inventorybridge.InventoryBridgeBlock;
import com.hlysine.create_connected.content.invertedclutch.InvertedClutchBlock;
import com.hlysine.create_connected.content.invertedgearshift.InvertedGearshiftBlock;
import com.hlysine.create_connected.content.itemsilo.ItemSiloBlock;
import com.hlysine.create_connected.content.itemsilo.ItemSiloCTBehaviour;
import com.hlysine.create_connected.content.itemsilo.ItemSiloItem;
import com.hlysine.create_connected.content.kineticbattery.KineticBatteryBlock;
import com.hlysine.create_connected.content.kineticbattery.KineticBatteryGenerator;
import com.hlysine.create_connected.content.kineticbridge.KineticBridgeBlock;
import com.hlysine.create_connected.content.kineticbridge.KineticBridgeBlockItem;
import com.hlysine.create_connected.content.kineticbridge.KineticBridgeDestinationBlock;
import com.hlysine.create_connected.content.linkedtransmitter.LinkedAnalogLeverBlock;
import com.hlysine.create_connected.content.linkedtransmitter.LinkedButtonBlock;
import com.hlysine.create_connected.content.linkedtransmitter.LinkedLeverBlock;
import com.hlysine.create_connected.content.linkedtransmitter.LinkedTransmitterItem;
import com.hlysine.create_connected.content.overstressclutch.OverstressClutchBlock;
import com.hlysine.create_connected.content.parallelgearbox.ParallelGearboxBlock;
import com.hlysine.create_connected.content.sequencedpulsegenerator.SequencedPulseGeneratorBlock;
import com.hlysine.create_connected.content.shearpin.ShearPinBlock;
import com.hlysine.create_connected.content.sixwaygearbox.SixWayGearboxBlock;
import com.hlysine.create_connected.datagen.CCBlockStateGen;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllSpriteShifts;
import com.simibubi.create.AllTags;
import com.simibubi.create.Create;
import com.simibubi.create.api.connectivity.ConnectivityHandler;
import com.simibubi.create.api.contraption.BlockMovementChecks;
import com.simibubi.create.api.contraption.storage.item.MountedItemStorageType;
import com.simibubi.create.api.stress.BlockStressValues;
import com.simibubi.create.content.decoration.encasing.EncasedCTBehaviour;
import com.simibubi.create.content.decoration.encasing.EncasingRegistry;
import com.simibubi.create.content.fluids.tank.FluidTankMovementBehavior;
import com.simibubi.create.content.kinetics.chainDrive.ChainDriveGenerator;
import com.simibubi.create.content.kinetics.simpleRelays.BracketedKineticBlockModel;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedShaftBlock;
import com.simibubi.create.foundation.block.ItemUseOverrides;
import com.simibubi.create.foundation.data.*;
import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateBlockstateProvider;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.createmod.catnip.data.Iterate;
import net.createmod.catnip.registry.RegisteredObjectsHelper;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.ExplosionCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.Tags;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static com.simibubi.create.api.behaviour.display.DisplaySource.displaySource;
import static com.simibubi.create.api.behaviour.movement.MovementBehaviour.movementBehaviour;
import static com.simibubi.create.api.contraption.storage.fluid.MountedFluidStorageType.mountedFluidStorage;
import static com.simibubi.create.foundation.data.AssetLookup.partialBaseModel;
import static com.simibubi.create.foundation.data.BlockStateGen.axisBlock;
import static com.simibubi.create.foundation.data.BlockStateGen.axisBlockProvider;
import static com.simibubi.create.foundation.data.CreateRegistrate.connectedTextures;
import static com.simibubi.create.foundation.data.ModelGen.customItemModel;
import static com.simibubi.create.foundation.data.TagGen.axeOrPickaxe;
import static com.simibubi.create.foundation.data.TagGen.pickaxeOnly;

@SuppressWarnings("removal")
public class CCBlocks {
    private static final CreateRegistrate REGISTRATE = CreateConnected.getRegistrate();

    public static final BlockEntry<ChainCogwheelBlock> ENCASED_CHAIN_COGWHEEL =
            REGISTRATE.block("encased_chain_cogwheel", ChainCogwheelBlock::new)
                    .initialProperties(SharedProperties::stone)
                    .properties(p -> p.noOcclusion().mapColor(MapColor.PODZOL))
                    .addLayer(() -> RenderType::cutoutMipped)
                    .transform(CStress.setNoImpact())
                    .transform(FeatureToggle.register(FeatureCategory.KINETIC))
                    .transform(axeOrPickaxe())
                    .blockstate((c, p) -> new ChainDriveGenerator((state, suffix) -> p.models()
                            .getExistingFile(p.modLoc("block/" + c.getName() + "/" + suffix))).generate(c, p))
                    .item()
                    .transform(customItemModel())
                    .register();

    public static final BlockEntry<CrankWheelBlock.Small> CRANK_WHEEL = REGISTRATE.block("crank_wheel", CrankWheelBlock.Small::new)
            .initialProperties(SharedProperties::wooden)
            .properties(p -> p.mapColor(MapColor.PODZOL))
            .transform(axeOrPickaxe())
            .blockstate(BlockStateGen.directionalBlockProvider(true))
            .transform(CStress.setCapacity(8.0))
            .onRegister(BlockStressValues.setGeneratorSpeed(32))
            .transform(FeatureToggle.register(FeatureCategory.KINETIC))
            .tag(AllTags.AllBlockTags.BRITTLE.tag)
            .onRegister(ItemUseOverrides::addBlock)
            .item()
            .transform(customItemModel())
            .register();

    public static final BlockEntry<CrankWheelBlock.Large> LARGE_CRANK_WHEEL = REGISTRATE.block("large_crank_wheel", CrankWheelBlock.Large::new)
            .initialProperties(SharedProperties::wooden)
            .properties(p -> p.mapColor(MapColor.PODZOL))
            .transform(axeOrPickaxe())
            .blockstate(BlockStateGen.directionalBlockProvider(true))
            .transform(CStress.setCapacity(8.0))
            .onRegister(BlockStressValues.setGeneratorSpeed(32))
            .transform(FeatureToggle.register(FeatureCategory.KINETIC))
            .tag(AllTags.AllBlockTags.BRITTLE.tag)
            .onRegister(ItemUseOverrides::addBlock)
            .item()
            .transform(customItemModel())
            .register();

    public static final BlockEntry<ParallelGearboxBlock> PARALLEL_GEARBOX = REGISTRATE.block("parallel_gearbox", ParallelGearboxBlock::new)
            .initialProperties(SharedProperties::stone)
            .properties(p -> p.noOcclusion().mapColor(MapColor.PODZOL))
            .transform(CStress.setNoImpact())
            .transform(FeatureToggle.register(FeatureCategory.KINETIC))
            .transform(axeOrPickaxe())
            .onRegister(CreateRegistrate.connectedTextures(() -> new EncasedCTBehaviour(AllSpriteShifts.ANDESITE_CASING)))
            .onRegister(CreateRegistrate.casingConnectivity((block, cc) -> cc.make(block, AllSpriteShifts.ANDESITE_CASING,
                    (s, f) -> f.getAxis() == s.getValue(ParallelGearboxBlock.AXIS))))
            .blockstate(CCBlockStateGen.axisBlock())
            .item()
            .transform(customItemModel())
            .register();

    public static final BlockEntry<SixWayGearboxBlock> SIX_WAY_GEARBOX = REGISTRATE.block("six_way_gearbox", SixWayGearboxBlock::new)
            .initialProperties(SharedProperties::stone)
            .properties(p -> p.noOcclusion().mapColor(MapColor.PODZOL))
            .addLayer(() -> RenderType::cutoutMipped)
            .transform(CStress.setNoImpact())
            .transform(FeatureToggle.register(FeatureCategory.KINETIC))
            .transform(axeOrPickaxe())
            .lang("6-way Gearbox")
            .blockstate((c, p) -> axisBlock(c, p, $ -> partialBaseModel(c, p), false))
            .item()
            .transform(customItemModel())
            .register();

    public static final BlockEntry<CrossConnectorBlock> CROSS_CONNECTOR = REGISTRATE.block("cross_connector", CrossConnectorBlock::new)
            .initialProperties(SharedProperties::stone)
            .properties(p -> p.noOcclusion().mapColor(MapColor.PODZOL))
            .addLayer(() -> RenderType::cutoutMipped)
            .transform(FeatureToggle.register(FeatureCategory.KINETIC))
            .transform(axeOrPickaxe())
            .blockstate((c, p) -> axisBlock(c, p, $ -> partialBaseModel(c, p), false))
            .item()
            .transform(customItemModel())
            .register();


    public static final BlockEntry<EncasedCrossConnectorBlock> ANDESITE_ENCASED_CROSS_CONNECTOR =
            REGISTRATE.block("andesite_encased_cross_connector", p -> new EncasedCrossConnectorBlock(p, AllBlocks.ANDESITE_CASING::get))
                    .properties(p -> p.mapColor(MapColor.PODZOL))
                    .transform(CCBuilderTransformers.encasedCrossConnector("andesite", () -> AllSpriteShifts.ANDESITE_CASING))
                    .transform(EncasingRegistry.addVariantTo(CCBlocks.CROSS_CONNECTOR))
                    .transform(FeatureToggle.registerDependent(CCBlocks.CROSS_CONNECTOR, FeatureCategory.KINETIC))
                    .transform(axeOrPickaxe())
                    .register();

    public static final BlockEntry<EncasedCrossConnectorBlock> BRASS_ENCASED_CROSS_CONNECTOR =
            REGISTRATE.block("brass_encased_cross_connector", p -> new EncasedCrossConnectorBlock(p, AllBlocks.BRASS_CASING::get))
                    .properties(p -> p.mapColor(MapColor.TERRACOTTA_BROWN))
                    .transform(CCBuilderTransformers.encasedCrossConnector("brass", () -> AllSpriteShifts.BRASS_CASING))
                    .transform(EncasingRegistry.addVariantTo(CCBlocks.CROSS_CONNECTOR))
                    .transform(FeatureToggle.registerDependent(CCBlocks.CROSS_CONNECTOR, FeatureCategory.KINETIC))
                    .transform(axeOrPickaxe())
                    .register();


    public static final BlockEntry<OverstressClutchBlock> OVERSTRESS_CLUTCH = REGISTRATE.block("overstress_clutch", OverstressClutchBlock::new)
            .initialProperties(SharedProperties::stone)
            .properties(p -> p.noOcclusion().mapColor(MapColor.PODZOL))
            .addLayer(() -> RenderType::cutoutMipped)
            .transform(CStress.setNoImpact())
            .transform(FeatureToggle.register(FeatureCategory.KINETIC))
            .transform(axeOrPickaxe())
            .blockstate((c, p) -> BlockStateGen.axisBlock(c, p, state -> {
                        if (state.getValue(OverstressClutchBlock.STATE) == OverstressClutchBlock.ClutchState.UNCOUPLED) {
                            if (state.getValue(OverstressClutchBlock.POWERED)) {
                                return partialBaseModel(c, p, "uncoupled", "powered");
                            } else {
                                return partialBaseModel(c, p, "uncoupled");
                            }
                        } else {
                            if (state.getValue(OverstressClutchBlock.POWERED)) {
                                return partialBaseModel(c, p, "powered");
                            } else {
                                return partialBaseModel(c, p);
                            }
                        }
                    })
            )
            .item()
            .transform(customItemModel())
            .register();


    public static final BlockEntry<ShearPinBlock> SHEAR_PIN = REGISTRATE.block("shear_pin", ShearPinBlock::new)
            .initialProperties(SharedProperties::stone)
            .properties(p -> p.mapColor(MapColor.METAL).forceSolidOn())
            .transform(CStress.setNoImpact())
            .transform(FeatureToggle.register(FeatureCategory.KINETIC))
            .transform(pickaxeOnly())
            .blockstate(BlockStateGen.axisBlockProvider(false))
            .onRegister(CreateRegistrate.blockModel(() -> BracketedKineticBlockModel::new))
            .simpleItem()
            .register();

    public static final BlockEntry<InvertedClutchBlock> INVERTED_CLUTCH = REGISTRATE.block("inverted_clutch", InvertedClutchBlock::new)
            .initialProperties(SharedProperties::stone)
            .properties(p -> p.noOcclusion().mapColor(MapColor.PODZOL))
            .addLayer(() -> RenderType::cutoutMipped)
            .transform(CStress.setNoImpact())
            .transform(FeatureToggle.register(FeatureCategory.KINETIC))
            .transform(axeOrPickaxe())
            .blockstate((c, p) -> BlockStateGen.axisBlock(c, p, AssetLookup.forPowered(c, p)))
            .item()
            .transform(customItemModel())
            .register();

    public static final BlockEntry<InvertedGearshiftBlock> INVERTED_GEARSHIFT = REGISTRATE.block("inverted_gearshift", InvertedGearshiftBlock::new)
            .initialProperties(SharedProperties::stone)
            .properties(p -> p.noOcclusion().mapColor(MapColor.PODZOL))
            .addLayer(() -> RenderType::cutoutMipped)
            .transform(CStress.setNoImpact())
            .transform(FeatureToggle.register(FeatureCategory.KINETIC))
            .transform(axeOrPickaxe())
            .blockstate((c, p) -> BlockStateGen.axisBlock(c, p, AssetLookup.forPowered(c, p)))
            .item()
            .transform(customItemModel())
            .register();


    public static final BlockEntry<CentrifugalClutchBlock> CENTRIFUGAL_CLUTCH = REGISTRATE.block("centrifugal_clutch", CentrifugalClutchBlock::new)
            .initialProperties(SharedProperties::stone)
            .properties(p -> p.noOcclusion().mapColor(MapColor.PODZOL))
            .addLayer(() -> RenderType::cutoutMipped)
            .transform(CStress.setNoImpact())
            .transform(FeatureToggle.register(FeatureCategory.KINETIC))
            .transform(axeOrPickaxe())
            .blockstate((c, p) -> p.directionalBlock(c.get(), forBoolean(c, state -> state.getValue(CentrifugalClutchBlock.UNCOUPLED), "uncoupled", p)))
            .item()
            .transform(customItemModel())
            .register();


    public static final BlockEntry<FreewheelClutchBlock> FREEWHEEL_CLUTCH = REGISTRATE.block("freewheel_clutch", FreewheelClutchBlock::new)
            .initialProperties(SharedProperties::stone)
            .properties(p -> p.noOcclusion().mapColor(MapColor.PODZOL))
            .addLayer(() -> RenderType::cutoutMipped)
            .transform(CStress.setNoImpact())
            .transform(FeatureToggle.register(FeatureCategory.KINETIC))
            .transform(axeOrPickaxe())
            .blockstate((c, p) -> p.directionalBlock(c.get(), forBoolean(c, state -> state.getValue(FreewheelClutchBlock.UNCOUPLED), "uncoupled", p)))
            .item()
            .transform(customItemModel())
            .register();


    public static final BlockEntry<KineticBridgeBlock> KINETIC_BRIDGE = REGISTRATE.block("kinetic_bridge", KineticBridgeBlock::new)
            .initialProperties(SharedProperties::stone)
            .properties(p -> p.noOcclusion().mapColor(MapColor.TERRACOTTA_BROWN))
            .addLayer(() -> RenderType::cutoutMipped)
            .transform(FeatureToggle.register(FeatureCategory.KINETIC))
            .transform(axeOrPickaxe())
            .onRegister(b -> BlockMovementChecks.registerAttachedCheck((state, world, pos, direction) -> {
                if (!(state.getBlock() instanceof KineticBridgeBlock))
                    return BlockMovementChecks.CheckResult.PASS;
                if (state.getValue(KineticBridgeBlock.FACING) != direction)
                    return BlockMovementChecks.CheckResult.PASS;
                return BlockMovementChecks.CheckResult.SUCCESS;
            }))
            .onRegister(b -> BlockMovementChecks.registerBrittleCheck(state -> {
                if (!(state.getBlock() instanceof KineticBridgeBlock))
                    return BlockMovementChecks.CheckResult.PASS;
                return BlockMovementChecks.CheckResult.SUCCESS;
            }))
            .blockstate((c, p) -> p.directionalBlock(c.get(), $ -> partialBaseModel(c, p)))
            .item(KineticBridgeBlockItem::new)
            .transform(customItemModel())
            .register();


    public static final BlockEntry<KineticBridgeDestinationBlock> KINETIC_BRIDGE_DESTINATION = REGISTRATE.block("kinetic_bridge_destination", KineticBridgeDestinationBlock::new)
            .initialProperties(SharedProperties::stone)
            .properties(p -> p.noOcclusion().mapColor(MapColor.TERRACOTTA_BROWN))
            .addLayer(() -> RenderType::cutoutMipped)
            .transform(FeatureToggle.registerDependent(CCBlocks.KINETIC_BRIDGE, FeatureCategory.KINETIC))
            .transform(axeOrPickaxe())
            .onRegister(b -> BlockMovementChecks.registerAttachedCheck((state, world, pos, direction) -> {
                if (!(state.getBlock() instanceof KineticBridgeDestinationBlock))
                    return BlockMovementChecks.CheckResult.PASS;
                if (state.getValue(KineticBridgeDestinationBlock.FACING).getOpposite() != direction)
                    return BlockMovementChecks.CheckResult.PASS;
                return BlockMovementChecks.CheckResult.SUCCESS;
            }))
            .blockstate((c, p) -> p.directionalBlock(c.get(),
                    $ -> p.models().getExistingFile(p.modLoc("block/kinetic_bridge/block_destination"))
            ))
            .lang("Kinetic Bridge")
            .register();

    public static final BlockEntry<BrassGearboxBlock> BRASS_GEARBOX = REGISTRATE.block("brass_gearbox", BrassGearboxBlock::new)
            .initialProperties(SharedProperties::stone)
            .properties(p -> p.noOcclusion().mapColor(MapColor.TERRACOTTA_BROWN))
            .transform(CStress.setNoImpact())
            .transform(FeatureToggle.register(FeatureCategory.KINETIC))
            .transform(axeOrPickaxe())
            .onRegister(CreateRegistrate.connectedTextures(() -> new EncasedCTBehaviour(AllSpriteShifts.BRASS_CASING)))
            .onRegister(CreateRegistrate.casingConnectivity((block, cc) -> cc.make(block, AllSpriteShifts.BRASS_CASING,
                    (s, f) -> f.getAxis() == s.getValue(BrassGearboxBlock.AXIS))))
            .blockstate(CCBlockStateGen.brassGearbox())
            .item()
            .transform(customItemModel())
            .register();

    public static final BlockEntry<BrakeBlock> BRAKE = REGISTRATE.block("brake", BrakeBlock::new)
            .initialProperties(SharedProperties::stone)
            .properties(p -> p.noOcclusion().mapColor(MapColor.PODZOL))
            .addLayer(() -> RenderType::cutoutMipped)
            .transform(CStress.setNoImpact()) // active stress is a separate config
            .transform(FeatureToggle.register(FeatureCategory.KINETIC))
            .transform(axeOrPickaxe())
            .blockstate((c, p) -> BlockStateGen.axisBlock(c, p, AssetLookup.forPowered(c, p)))
            .item()
            .transform(customItemModel())
            .register();

    public static final BlockEntry<KineticBatteryBlock> KINETIC_BATTERY = REGISTRATE.block("kinetic_battery", KineticBatteryBlock::new)
            .initialProperties(SharedProperties::stone)
            .properties(p -> p.noOcclusion().mapColor(MapColor.TERRACOTTA_BROWN))
            .addLayer(() -> RenderType::cutoutMipped)
            .transform(CStress.setCapacity(32.0))
            .transform(CStress.setImpact(64.0))
            .transform(FeatureToggle.register(FeatureCategory.KINETIC))
            .transform(axeOrPickaxe())
            .blockstate(new KineticBatteryGenerator()::generate)
            .loot((lt, block) -> {
                LootTable.Builder builder = LootTable.lootTable();
                builder.withPool(
                        LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1.0F))
                                .when(ExplosionCondition.survivesExplosion())
                                .when(LootItemBlockStatePropertyCondition
                                        .hasBlockStateProperties(block)
                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(KineticBatteryBlock.LEVEL, 5)))
                                .add(LootItem.lootTableItem(CCItems.CHARGED_KINETIC_BATTERY))
                );
                builder.withPool(
                        LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1.0F))
                                .when(ExplosionCondition.survivesExplosion())
                                .when(LootItemBlockStatePropertyCondition
                                        .hasBlockStateProperties(block)
                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(KineticBatteryBlock.LEVEL, 5))
                                        .invert())
                                .add(LootItem.lootTableItem(block))
                );
                lt.add(block, builder);
            })
            .item()
            .transform(customItemModel())
            .register();

    public static final BlockEntry<SequencedPulseGeneratorBlock> SEQUENCED_PULSE_GENERATOR =
            REGISTRATE.block("sequenced_pulse_generator", SequencedPulseGeneratorBlock::new)
                    .initialProperties(() -> Blocks.REPEATER)
                    .tag(AllTags.AllBlockTags.SAFE_NBT.tag)
                    .blockstate(CCBlockStateGen.sequencedPulseGenerator())
                    .transform(FeatureToggle.register(FeatureCategory.REDSTONE))
                    .addLayer(() -> RenderType::cutoutMipped)
                    .simpleItem()
                    .register();

    public static final Map<BlockSetType, BlockEntry<LinkedButtonBlock>> LINKED_BUTTONS = new HashMap<>();

    static {
        BlockSetType.values().forEach(type -> {
            Block button = RegisteredObjectsHelper.getBlock(ResourceLocation.parse(type.name() + "_button"));
            if (button == null) return;
            if (!(button instanceof ButtonBlock buttonBlock))
                return;
            String namePath = type.name().contains(":") ? type.name().replace(':', '_') : type.name();
            LINKED_BUTTONS.put(type, REGISTRATE
                    .block("linked_" + namePath + "_button", properties -> new LinkedButtonBlock(properties, buttonBlock))
                    .initialProperties(() -> buttonBlock)
                    .tag(AllTags.AllBlockTags.SAFE_NBT.tag)
                    .transform(LinkedTransmitterItem.register())
                    .onRegister(PreciseItemUseOverrides::addBlock)
                    .blockstate(CCBlockStateGen.linkedButton(
                            ResourceLocation.withDefaultNamespace("block/" + namePath + "_button"),
                            ResourceLocation.withDefaultNamespace("block/" + namePath + "_button_pressed")
                    ))
                    .register());
        });
    }

    public static final BlockEntry<LinkedLeverBlock> LINKED_LEVER = REGISTRATE
            .block("linked_lever", properties -> new LinkedLeverBlock(properties, (LeverBlock) Blocks.LEVER))
            .initialProperties(() -> Blocks.LEVER)
            .tag(AllTags.AllBlockTags.SAFE_NBT.tag)
            .transform(LinkedTransmitterItem.register())
            .onRegister(PreciseItemUseOverrides::addBlock)
            .blockstate(CCBlockStateGen.linkedLever(
                    ResourceLocation.withDefaultNamespace("block/lever"),
                    ResourceLocation.withDefaultNamespace("block/lever_on")
            ))
            .register();

    public static final BlockEntry<LinkedAnalogLeverBlock> LINKED_ANALOG_LEVER = REGISTRATE
            .block("linked_analog_lever", properties -> new LinkedAnalogLeverBlock(properties, AllBlocks.ANALOG_LEVER))
            .initialProperties(() -> Blocks.LEVER)
            .tag(AllTags.AllBlockTags.SAFE_NBT.tag)
            .transform(LinkedTransmitterItem.register())
            .onRegister(PreciseItemUseOverrides::addBlock)
            .blockstate(CCBlockStateGen.linkedLever(
                    Create.asResource("block/analog_lever/block"),
                    Create.asResource("block/analog_lever/block")
            ))
            .register();

    public static final BlockEntry<WrenchableBlock> EMPTY_FAN_CATALYST = REGISTRATE.block("empty_fan_catalyst", WrenchableBlock::new)
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .properties(p -> p
                    .mapColor(MapColor.TERRACOTTA_YELLOW)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .isRedstoneConductor((state, level, pos) -> false)
            )
            .addLayer(() -> RenderType::cutoutMipped)
            .transform(pickaxeOnly())
            .transform(FeatureToggle.register(FeatureCategory.LOGISTICS))
            .blockstate((c, p) -> p.simpleBlock(c.getEntry(), AssetLookup.partialBaseModel(c, p)))
            .tag(AllTags.AllBlockTags.FAN_TRANSPARENT.tag)
            .item()
            .transform(customItemModel())
            .register();

    public static final BlockEntry<WrenchableBlock> FAN_BLASTING_CATALYST = REGISTRATE.block("fan_blasting_catalyst", WrenchableBlock::new)
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .properties(p -> p
                    .mapColor(MapColor.TERRACOTTA_YELLOW)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .lightLevel(s -> 10)
                    .isRedstoneConductor((state, level, pos) -> false)
            )
            .addLayer(() -> RenderType::cutoutMipped)
            .transform(pickaxeOnly())
            .transform(FeatureToggle.registerDependent(CCBlocks.EMPTY_FAN_CATALYST))
            .blockstate((c, p) -> p.simpleBlock(c.getEntry(), AssetLookup.partialBaseModel(c, p)))
            .tag(AllTags.AllBlockTags.FAN_TRANSPARENT.tag)
            .tag(AllTags.AllBlockTags.FAN_PROCESSING_CATALYSTS_BLASTING.tag)
            .item()
            .transform(customItemModel())
            .register();

    public static final BlockEntry<WrenchableBlock> FAN_SMOKING_CATALYST = REGISTRATE.block("fan_smoking_catalyst", WrenchableBlock::new)
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .properties(p -> p
                    .mapColor(MapColor.TERRACOTTA_YELLOW)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .lightLevel(s -> 10)
                    .isRedstoneConductor((state, level, pos) -> false)
            )
            .addLayer(() -> RenderType::cutoutMipped)
            .transform(pickaxeOnly())
            .transform(FeatureToggle.registerDependent(CCBlocks.EMPTY_FAN_CATALYST))
            .blockstate((c, p) -> p.simpleBlock(c.getEntry(), AssetLookup.partialBaseModel(c, p)))
            .tag(AllTags.AllBlockTags.FAN_TRANSPARENT.tag)
            .tag(AllTags.AllBlockTags.FAN_PROCESSING_CATALYSTS_SMOKING.tag)
            .item()
            .transform(customItemModel())
            .register();

    public static final BlockEntry<WrenchableBlock> FAN_SPLASHING_CATALYST = REGISTRATE.block("fan_splashing_catalyst", WrenchableBlock::new)
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .properties(p -> p
                    .mapColor(MapColor.TERRACOTTA_YELLOW)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .isRedstoneConductor((state, level, pos) -> false)
            )
            .transform(pickaxeOnly())
            .transform(FeatureToggle.registerDependent(CCBlocks.EMPTY_FAN_CATALYST))
            .blockstate((c, p) -> p.simpleBlock(c.getEntry(), AssetLookup.partialBaseModel(c, p)))
            .color(() -> CCColorHandlers::waterBlockTint)
            .lang("Fan Washing Catalyst")
            .tag(AllTags.AllBlockTags.FAN_TRANSPARENT.tag)
            .tag(AllTags.AllBlockTags.FAN_PROCESSING_CATALYSTS_SPLASHING.tag)
            .item()
            .color(() -> CCColorHandlers::waterItemTint)
            .transform(customItemModel())
            .register();

    public static final BlockEntry<WrenchableBlock> FAN_HAUNTING_CATALYST = REGISTRATE.block("fan_haunting_catalyst", WrenchableBlock::new)
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .properties(p -> p
                    .mapColor(MapColor.TERRACOTTA_YELLOW)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .lightLevel(s -> 5)
                    .isRedstoneConductor((state, level, pos) -> false)
            )
            .addLayer(() -> RenderType::cutoutMipped)
            .transform(pickaxeOnly())
            .transform(FeatureToggle.registerDependent(CCBlocks.EMPTY_FAN_CATALYST))
            .blockstate((c, p) -> p.simpleBlock(c.getEntry(), AssetLookup.partialBaseModel(c, p)))
            .tag(AllTags.AllBlockTags.FAN_TRANSPARENT.tag)
            .tag(AllTags.AllBlockTags.FAN_PROCESSING_CATALYSTS_HAUNTING.tag)
            .item()
            .transform(customItemModel())
            .register();

    public static final BlockEntry<WrenchableBlock> FAN_FREEZING_CATALYST = REGISTRATE.block("fan_freezing_catalyst", WrenchableBlock::new)
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .properties(p -> p
                    .mapColor(MapColor.TERRACOTTA_YELLOW)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .isRedstoneConductor((state, level, pos) -> false)
            )
            .addLayer(() -> RenderType::cutoutMipped)
            .transform(pickaxeOnly())
            .transform(FeatureToggle.registerDependent(CCBlocks.EMPTY_FAN_CATALYST))
            .transform(FeatureToggle.addCondition(() -> Mods.GARNISHED.isLoaded() || Mods.DREAMS_DESIRES.isLoaded() || Mods.DRAGONS_PLUS.isLoaded()))
            .blockstate((c, p) -> p.simpleBlock(c.getEntry(), AssetLookup.partialBaseModel(c, p)))
            .tag(AllTags.AllBlockTags.FAN_TRANSPARENT.tag)
            .item()
            .transform(customItemModel())
            .register();

    public static final BlockEntry<WrenchableBlock> FAN_SEETHING_CATALYST = REGISTRATE.block("fan_seething_catalyst", WrenchableBlock::new)
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .properties(p -> p
                    .mapColor(MapColor.TERRACOTTA_YELLOW)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .lightLevel(s -> 12)
                    .isRedstoneConductor((state, level, pos) -> false)
            )
            .addLayer(() -> RenderType::cutoutMipped)
            .transform(pickaxeOnly())
            .transform(FeatureToggle.registerDependent(CCBlocks.EMPTY_FAN_CATALYST))
            .transform(FeatureToggle.addCondition(Mods.DREAMS_DESIRES::isLoaded))
            .blockstate((c, p) -> p.simpleBlock(c.getEntry(), AssetLookup.partialBaseModel(c, p)))
            .tag(AllTags.AllBlockTags.FAN_TRANSPARENT.tag)
            .item()
            .transform(customItemModel())
            .register();

    public static final BlockEntry<WrenchableBlock> FAN_SANDING_CATALYST = REGISTRATE.block("fan_sanding_catalyst", WrenchableBlock::new)
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .properties(p -> p
                    .mapColor(MapColor.TERRACOTTA_YELLOW)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .isRedstoneConductor((state, level, pos) -> false)
            )
            .addLayer(() -> RenderType::cutoutMipped)
            .transform(pickaxeOnly())
            .transform(FeatureToggle.registerDependent(CCBlocks.EMPTY_FAN_CATALYST))
            .transform(FeatureToggle.addCondition(() -> Mods.DREAMS_DESIRES.isLoaded() || Mods.DRAGONS_PLUS.isLoaded()))
            .blockstate((c, p) -> p.simpleBlock(c.getEntry(), AssetLookup.partialBaseModel(c, p)))
            .tag(AllTags.AllBlockTags.FAN_TRANSPARENT.tag)
            .item()
            .transform(customItemModel())
            .register();

    public static final BlockEntry<WrenchableBlock> FAN_ENRICHED_CATALYST = REGISTRATE.block("fan_enriched_catalyst", WrenchableBlock::new)
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .properties(p -> p
                    .mapColor(MapColor.TERRACOTTA_YELLOW)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .lightLevel(s -> 13)
                    .isRedstoneConductor((state, level, pos) -> false)
            )
            .addLayer(() -> RenderType::cutoutMipped)
            .transform(pickaxeOnly())
            .transform(FeatureToggle.registerDependent(CCBlocks.EMPTY_FAN_CATALYST))
            .transform(FeatureToggle.addCondition(Mods.NUCLEAR::isLoaded))
            .blockstate((c, p) -> p.simpleBlock(c.getEntry(), AssetLookup.partialBaseModel(c, p)))
            .tag(AllTags.AllBlockTags.FAN_TRANSPARENT.tag)
            .item()
            .transform(customItemModel())
            .register();

    public static final BlockEntry<WrenchableBlock> FAN_ENDING_CATALYST = REGISTRATE.block("fan_ending_catalyst", WrenchableBlock::new)
            .initialProperties(() -> Blocks.IRON_BLOCK)
            .properties(p -> p
                    .mapColor(MapColor.TERRACOTTA_YELLOW)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .lightLevel(s -> 15)
                    .isRedstoneConductor((state, level, pos) -> false)
            )
            .addLayer(() -> RenderType::cutoutMipped)
            .transform(pickaxeOnly())
            .transform(FeatureToggle.registerDependent(CCBlocks.EMPTY_FAN_CATALYST))
            .transform(FeatureToggle.addCondition(Mods.DRAGONS_PLUS::isLoaded))
            .blockstate((c, p) -> p.simpleBlock(c.getEntry(), AssetLookup.partialBaseModel(c, p)))
            .tag(AllTags.AllBlockTags.FAN_TRANSPARENT.tag)
            .item()
            .transform(customItemModel())
            .register();

    public static final BlockEntry<ItemSiloBlock> ITEM_SILO = REGISTRATE.block("item_silo", ItemSiloBlock::new)
            .initialProperties(SharedProperties::softMetal)
            .properties(p -> p.mapColor(MapColor.TERRACOTTA_BLUE).sound(SoundType.NETHERITE_BLOCK)
                    .explosionResistance(1200))
            .transform(pickaxeOnly())
            .transform(FeatureToggle.register(FeatureCategory.LOGISTICS))
            .blockstate((c, p) -> p.getVariantBuilder(c.get())
                    .forAllStates(s -> ConfiguredModel.builder()
                            .modelFile(AssetLookup.standardModel(c, p))
                            .build()))
            .onRegister(connectedTextures(ItemSiloCTBehaviour::new))
            .transform(MountedItemStorageType.mountedItemStorage(CCMountedStorageTypes.SILO))
            .onRegister(b -> BlockMovementChecks.registerAttachedCheck((state, world, pos, direction) -> {
                if (state.getBlock() instanceof ItemSiloBlock)
                    return BlockMovementChecks.CheckResult.of(ConnectivityHandler.isConnected(world, pos, pos.relative(direction)));
                return BlockMovementChecks.CheckResult.PASS;
            }))
            .item(ItemSiloItem::new)
            .build()
            .register();

    public static final BlockEntry<FluidVesselBlock> FLUID_VESSEL = REGISTRATE.block("fluid_vessel", FluidVesselBlock::regular)
            .initialProperties(SharedProperties::copperMetal)
            .properties(p -> p.noOcclusion().isRedstoneConductor((p1, p2, p3) -> true))
            .transform(pickaxeOnly())
            .transform(FeatureToggle.register(FeatureCategory.LOGISTICS))
            .blockstate(new FluidVesselGenerator()::generate)
            .onRegister(CreateRegistrate.blockModel(() -> FluidVesselModel::standard))
            .onRegister(b -> BlockMovementChecks.registerAttachedCheck((state, world, pos, direction) -> {
                if (state.getBlock() instanceof FluidVesselBlock)
                    return BlockMovementChecks.CheckResult.of(ConnectivityHandler.isConnected(world, pos, pos.relative(direction)));
                return BlockMovementChecks.CheckResult.PASS;
            }))
            .transform(displaySource(CCDisplaySources.BOILER_STATUS))
            .transform(mountedFluidStorage(CCMountedStorageTypes.FLUID_VESSEL))
            .onRegister(movementBehaviour(new FluidTankMovementBehavior()))
            .addLayer(() -> RenderType::cutoutMipped)
            .item(FluidVesselItem::new)
            .model(AssetLookup.customBlockItemModel("_", "block_x_single_window"))
            .build()
            .register();

    public static final BlockEntry<FluidVesselBlock> CREATIVE_FLUID_VESSEL =
            REGISTRATE.block("creative_fluid_vessel", FluidVesselBlock::creative)
                    .initialProperties(SharedProperties::copperMetal)
                    .properties(p -> p.noOcclusion().mapColor(MapColor.COLOR_PURPLE))
                    .transform(pickaxeOnly())
                    .transform(FeatureToggle.registerDependent(FLUID_VESSEL))
                    .tag(AllTags.AllBlockTags.SAFE_NBT.tag)
                    .blockstate(new FluidVesselGenerator("creative_")::generate)
                    .onRegister(CreateRegistrate.blockModel(() -> FluidVesselModel::creative))
                    .addLayer(() -> RenderType::cutoutMipped)
                    .item(FluidVesselItem::new)
                    .properties(p -> p.rarity(Rarity.EPIC))
                    .model((c, p) -> p.withExistingParent(c.getName(), p.modLoc("block/fluid_vessel/block_x_single_window"))
                            .texture("5", Create.asResource("block/creative_fluid_tank_window_single"))
                            .texture("1", Create.asResource("block/creative_fluid_tank"))
                            .texture("particle", Create.asResource("block/creative_fluid_tank"))
                            .texture("4", Create.asResource("block/creative_casing"))
                            .texture("6", p.modLoc("block/fluid_container_window"))
                            .texture("7", p.modLoc("block/creative_fluid_container_window_single"))
                            .texture("0", Create.asResource("block/creative_casing")))
                    .build()
                    .register();

    public static final BlockEntry<InventoryAccessPortBlock> INVENTORY_ACCESS_PORT =
            REGISTRATE.block("inventory_access_port", InventoryAccessPortBlock::new)
                    .initialProperties(SharedProperties::stone)
                    .properties(p -> p.mapColor(MapColor.TERRACOTTA_BROWN).noOcclusion())
                    .transform(axeOrPickaxe())
                    .transform(FeatureToggle.register(FeatureCategory.LOGISTICS))
                    .blockstate(new InventoryAccessPortGenerator()::generate)
                    .item()
                    .transform(customItemModel("_", "block_wall"))
                    .register();

    public static final BlockEntry<InventoryBridgeBlock> INVENTORY_BRIDGE =
            REGISTRATE.block("inventory_bridge", InventoryBridgeBlock::new)
                    .initialProperties(SharedProperties::stone)
                    .properties(p -> p.mapColor(MapColor.TERRACOTTA_BROWN).noOcclusion())
                    .transform(axeOrPickaxe())
                    .transform(FeatureToggle.register(FeatureCategory.LOGISTICS))
                    .blockstate((c, p) -> BlockStateGen.axisBlock(c, p, state -> {
                        boolean negative = state.getValue(InventoryBridgeBlock.ATTACHED_NEGATIVE);
                        boolean positive = state.getValue(InventoryBridgeBlock.ATTACHED_POSITIVE);
                        if (negative && positive)
                            return AssetLookup.partialBaseModel(c, p, "both");
                        if (negative)
                            return AssetLookup.partialBaseModel(c, p, "negative");
                        if (positive)
                            return AssetLookup.partialBaseModel(c, p, "positive");
                        return AssetLookup.partialBaseModel(c, p);
                    }))
                    .item()
                    .transform(customItemModel())
                    .register();

    public static final BlockEntry<CopycatSlabBlock> COPYCAT_SLAB =
            REGISTRATE.block("copycat_slab", CopycatSlabBlock::new)
                    .transform(BuilderTransformers.copycat())
                    .tag(BlockTags.SLABS)
                    .transform(FeatureToggle.register(FeatureCategory.COPYCATS))
                    .loot((lt, block) -> lt.add(block, lt.createSlabItemTable(block)))
                    .onRegister(CreateRegistrate.blockModel(() -> CopycatSlabModel::new))
                    .item()
                    .tag(CCTags.Items.COPYCAT_SLAB.tag)
                    .transform(customItemModel("copycat_base", "slab"))
                    .register();

    public static final BlockEntry<CopycatBlockBlock> COPYCAT_BLOCK =
            REGISTRATE.block("copycat_block", CopycatBlockBlock::new)
                    .transform(BuilderTransformers.copycat())
                    .transform(FeatureToggle.register(FeatureCategory.COPYCATS))
                    .onRegister(CreateRegistrate.blockModel(() -> CopycatBlockModel::new))
                    .item()
                    .tag(CCTags.Items.COPYCAT_BLOCK.tag)
                    .transform(customItemModel("copycat_base", "block"))
                    .register();

    public static final BlockEntry<CopycatBeamBlock> COPYCAT_BEAM =
            REGISTRATE.block("copycat_beam", CopycatBeamBlock::new)
                    .transform(BuilderTransformers.copycat())
                    .transform(FeatureToggle.register(FeatureCategory.COPYCATS))
                    .onRegister(CreateRegistrate.blockModel(() -> CopycatBeamModel::new))
                    .item()
                    .tag(CCTags.Items.COPYCAT_BEAM.tag)
                    .transform(customItemModel("copycat_base", "beam"))
                    .register();

    public static final BlockEntry<CopycatVerticalStepBlock> COPYCAT_VERTICAL_STEP =
            REGISTRATE.block("copycat_vertical_step", CopycatVerticalStepBlock::new)
                    .transform(BuilderTransformers.copycat())
                    .transform(FeatureToggle.register(FeatureCategory.COPYCATS))
                    .onRegister(CreateRegistrate.blockModel(() -> CopycatVerticalStepModel::new))
                    .item()
                    .tag(CCTags.Items.COPYCAT_VERTICAL_STEP.tag)
                    .transform(customItemModel("copycat_base", "vertical_step"))
                    .register();

    public static final BlockEntry<CopycatStairsBlock> COPYCAT_STAIRS =
            REGISTRATE.block("copycat_stairs", CopycatStairsBlock::new)
                    .transform(BuilderTransformers.copycat())
                    .tag(BlockTags.STAIRS)
                    .transform(FeatureToggle.register(FeatureCategory.COPYCATS))
                    .onRegister(CreateRegistrate.blockModel(() -> CopycatStairsModel::new))
                    .item()
                    .tag(CCTags.Items.COPYCAT_STAIRS.tag)
                    .transform(customItemModel("copycat_base", "stairs"))
                    .register();

    public static final BlockEntry<WrappedStairsBlock> WRAPPED_COPYCAT_STAIRS =
            REGISTRATE.block("wrapped_copycat_stairs", p -> new WrappedStairsBlock(Blocks.STONE.defaultBlockState(), p))
                    .initialProperties(() -> Blocks.STONE_STAIRS)
                    .onRegister(b -> CopycatStairsBlock.stairs = b)
                    .tag(BlockTags.STAIRS)
                    .blockstate((c, p) -> p.simpleBlock(c.getEntry(), p.models().withExistingParent("wrapped_copycat_stairs", "block/barrier")))
                    .register();

    public static final BlockEntry<CopycatFenceBlock> COPYCAT_FENCE =
            REGISTRATE.block("copycat_fence", CopycatFenceBlock::new)
                    .transform(BuilderTransformers.copycat())
                    .tag(BlockTags.FENCES, Tags.Blocks.FENCES)
                    .transform(FeatureToggle.register(FeatureCategory.COPYCATS))
                    .onRegister(CreateRegistrate.blockModel(() -> CopycatFenceModel::new))
                    .item()
                    .tag(CCTags.Items.COPYCAT_FENCE.tag)
                    .transform(customItemModel("copycat_base", "fence"))
                    .register();

    public static final BlockEntry<WrappedFenceBlock> WRAPPED_COPYCAT_FENCE =
            REGISTRATE.block("wrapped_copycat_fence", WrappedFenceBlock::new)
                    .initialProperties(() -> Blocks.OAK_FENCE)
                    .onRegister(b -> CopycatFenceBlock.fence = b)
                    .tag(BlockTags.FENCES, Tags.Blocks.FENCES)
                    .blockstate((c, p) -> p.simpleBlock(c.getEntry(), p.models().withExistingParent("wrapped_copycat_fence", "block/barrier")))
                    .register();

    public static final BlockEntry<CopycatWallBlock> COPYCAT_WALL =
            REGISTRATE.block("copycat_wall", CopycatWallBlock::new)
                    .transform(BuilderTransformers.copycat())
                    .properties(p -> p.forceSolidOn())
                    .tag(BlockTags.WALLS)
                    .transform(FeatureToggle.register(FeatureCategory.COPYCATS))
                    .onRegister(CreateRegistrate.blockModel(() -> CopycatWallModel::new))
                    .item()
                    .tag(CCTags.Items.COPYCAT_WALL.tag)
                    .transform(customItemModel("copycat_base", "wall"))
                    .register();

    public static final BlockEntry<WrappedWallBlock> WRAPPED_COPYCAT_WALL =
            REGISTRATE.block("wrapped_copycat_wall", WrappedWallBlock::new)
                    .initialProperties(() -> Blocks.COBBLESTONE_WALL)
                    .onRegister(b -> CopycatWallBlock.wall = b)
                    .tag(BlockTags.WALLS)
                    .blockstate((c, p) -> p.simpleBlock(c.getEntry(), p.models().withExistingParent("wrapped_copycat_wall", "block/barrier")))
                    .register();

    public static final BlockEntry<CopycatFenceGateBlock> COPYCAT_FENCE_GATE =
            REGISTRATE.block("copycat_fence_gate", CopycatFenceGateBlock::new)
                    .transform(BuilderTransformers.copycat())
                    .properties(p -> p.forceSolidOn())
                    .tag(BlockTags.FENCE_GATES, Tags.Blocks.FENCE_GATES, BlockTags.UNSTABLE_BOTTOM_CENTER, AllTags.AllBlockTags.MOVABLE_EMPTY_COLLIDER.tag)
                    .transform(FeatureToggle.register(FeatureCategory.COPYCATS))
                    .onRegister(CreateRegistrate.blockModel(() -> CopycatFenceGateModel::new))
                    .item()
                    .tag(CCTags.Items.COPYCAT_FENCE_GATE.tag)
                    .transform(customItemModel("copycat_base", "fence_gate"))
                    .register();

    public static final BlockEntry<WrappedFenceGateBlock> WRAPPED_COPYCAT_FENCE_GATE =
            REGISTRATE.block("wrapped_copycat_fence_gate", p -> new WrappedFenceGateBlock(WoodType.OAK, p))
                    .initialProperties(() -> Blocks.OAK_FENCE_GATE)
                    .onRegister(b -> CopycatFenceGateBlock.fenceGate = b)
                    .tag(BlockTags.FENCE_GATES, Tags.Blocks.FENCE_GATES, BlockTags.UNSTABLE_BOTTOM_CENTER, AllTags.AllBlockTags.MOVABLE_EMPTY_COLLIDER.tag)
                    .blockstate((c, p) -> p.simpleBlock(c.getEntry(), p.models().withExistingParent("wrapped_copycat_fence_gate", "block/barrier")))
                    .register();

    public static final BlockEntry<CopycatBoardBlock> COPYCAT_BOARD =
            REGISTRATE.block("copycat_board", CopycatBoardBlock::new)
                    .transform(BuilderTransformers.copycat())
                    .transform(FeatureToggle.register(FeatureCategory.COPYCATS))
                    .onRegister(CreateRegistrate.blockModel(() -> CopycatBoardModel::new))
                    .loot((lt, block) -> {
                        LootTable.Builder builder = LootTable.lootTable();
                        for (Direction direction : Iterate.directions) {
                            builder.withPool(
                                    LootPool.lootPool()
                                            .setRolls(ConstantValue.exactly(1.0F))
                                            .when(ExplosionCondition.survivesExplosion())
                                            .when(LootItemBlockStatePropertyCondition
                                                    .hasBlockStateProperties(block)
                                                    .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CopycatBoardBlock.byDirection(direction), true)))
                                            .add(LootItem.lootTableItem(block))
                            );
                        }
                        lt.add(block, builder);
                    })
                    .item()
                    .tag(CCTags.Items.COPYCAT_BOARD.tag)
                    .transform(customItemModel("copycat_base", "board"))
                    .register();

    public static void register() {
    }

    private static Function<BlockState, ModelFile> forBoolean(DataGenContext<?, ?> ctx,
                                                              Function<BlockState, Boolean> condition,
                                                              String key,
                                                              RegistrateBlockstateProvider prov) {
        return state -> condition.apply(state) ? partialBaseModel(ctx, prov, key)
                : partialBaseModel(ctx, prov);
    }
}
