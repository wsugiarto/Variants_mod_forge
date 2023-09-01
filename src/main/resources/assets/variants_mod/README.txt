THESE FILES MUST HAVE THE SAME FILE NAME AS THEIR ID

for the item json:
"item/generated" just kinda make the texture look 3dish in the inventory
blocks also need to have a texture here (for in the inventory)

for the block json:
"parent": "minecraft:block/cube_all" means all sides of the cube uses the texture given

blockstates is to determine the models blocks takes on when placed / created on the world


so when adding a block: remember to add on ModBlock.java, blockstates, block model, item model, texture