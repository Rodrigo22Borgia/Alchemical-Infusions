package com.rodrigo.entities;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Hand;
import net.minecraft.util.collection.DefaultedList;

import java.util.function.Predicate;

public interface ImplementedInventory extends Inventory {
    /**
     * Assigns the player's main hand item into the [int] slot of this entity.
     * Predicate allows to filter what items are allowed into the slot
     * Returns true on swap or empty both slots, false if no swap occurred.
     */
    default boolean itemAssign(int slot, PlayerEntity player, Predicate<ItemStack> predicate) {
        ItemStack inHand = player.getMainHandStack();
        ItemStack inSlot = this.getStack(slot);
        if (!inHand.isEmpty() && inHand.getItem() == inSlot.getItem() && matchingNBT(inHand, inSlot)) {
            if (predicate.test(inHand)) {
                int count = inSlot.getCount() + inHand.getCount();
                int max = inHand.getMaxCount();
                inHand.setCount(Math.max(count- max, 0));
                inSlot.setCount(Math.min(count, max));
            } else {
                player.giveOrDropStack(inSlot);
                inSlot.setCount(0);
            }
        } else if (inHand.isEmpty() || predicate.test(inHand)) {
            player.setStackInHand(Hand.MAIN_HAND, getStack(slot));
            setStack(slot, inHand);
        } else {
            return false;
        }
        markDirty();
        return true;
    }

    static boolean matchingNBT(ItemStack stack1, ItemStack stack2) {
        NbtCompound nbt1 = new NbtCompound();
        NbtCompound nbt2 = new NbtCompound();
        nbt1.copyFromCodec(ItemStack.MAP_CODEC, stack1);
        nbt2.copyFromCodec(ItemStack.MAP_CODEC, stack2);
        nbt1.remove("count");
        nbt2.remove("count");
        return nbt1.equals(nbt2);
    }

    /**
     * Retrieves the item list of this inventory.
     * Must return the same instance every time it's called.
     */
    DefaultedList<ItemStack> getItems();

    /**
     * Creates an inventory from the item list.
     */
    static ImplementedInventory of(DefaultedList<ItemStack> items) {
        return () -> items;
    }

    /**
     * Creates a new inventory with the specified size.
     */
    static ImplementedInventory ofSize(int size) {
        return of(DefaultedList.ofSize(size, ItemStack.EMPTY));
    }

    /**
     * Returns the inventory size.
     */
    @Override
    default int size() {
        return getItems().size();
    }

    /**
     * Checks if the inventory is empty.
     * @return true if this inventory has only empty stacks, false otherwise.
     */
    @Override
    default boolean isEmpty() {
        for (int i = 0; i < size(); i++) {
            ItemStack stack = getStack(i);
            if (!stack.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Retrieves the item in the slot.
     */
    @Override
    default ItemStack getStack(int slot) {
        return getItems().get(slot);
    }

    /**
     * Removes items from an inventory slot.
     *
     * @param slot  The slot to remove from.
     * @param count How many items to remove. If there are less items in the slot than what are requested,
     *              takes all items in that slot.
     */
    @Override
    default ItemStack removeStack(int slot, int count) {
        ItemStack result = Inventories.splitStack(getItems(), slot, count);
        if (!result.isEmpty()) {
            markDirty();
        }
        return result;
    }

    /**
     * Removes all items from an inventory slot.
     * @param slot The slot to remove from.
     */
    @Override
    default ItemStack removeStack(int slot) {
        return Inventories.removeStack(getItems(), slot);
    }

    /**
     * Replaces the current stack in an inventory slot with the provided stack.
     * @param slot  The inventory slot of which to replace the itemstack.
     * @param stack The replacing itemstack. If the stack is too big for
     *              this inventory ({@link Inventory#getMaxCountPerStack()}),
     *              it gets resized to this inventory's maximum amount.
     */
    @Override
    default void setStack(int slot, ItemStack stack) {
        getItems().set(slot, stack);
        if (stack.getCount() > stack.getMaxCount()) {
            stack.setCount(stack.getMaxCount());
        }
    }

    /**
     * Clears the inventory.
     */
    @Override
    default void clear() {
        getItems().clear();
    }

    /**
     * Marks the state as dirty.
     * Must be called after changes in the inventory, so that the game can properly save
     * the inventory contents and notify neighboring blocks of inventory changes.
     */
    @Override
    default void markDirty() {
        // Override if you want behavior.
    }

    /**
     * @return true if the player can use the inventory, false otherwise.
     */
    @Override
    default boolean canPlayerUse(PlayerEntity player) {
        return true;
    }
}
