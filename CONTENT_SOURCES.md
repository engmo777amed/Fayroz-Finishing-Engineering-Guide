# Field guide content and validation

The guide keeps its 30 categories, existing stage IDs, Compose screens, Cairo fonts, colors, and Room schema. The catalog adds 58 field phases and 58 material cards: 96 stages and 131 materials in total. Existing instructions remain available; incorrect references and unconditional product limits are corrected in their original records.

Each phase uses the existing objective, prerequisites, materials/tools, execution, dimensions, mixes, technical requirements, MEP coordination, tips, mistakes, testing, handover, and reference fields. Missing checklist phases reuse their own quality tests and handover criteria as read-only guidance, and the existing cards now display the inspection method. Alternative systems are selected from project requirements rather than layered automatically.

Types, quality grades, uses, and advantages/disadvantages now appear inside the existing material cards. Related-material links use explicit stage relationships in both directions.

## Primary reference entry points

- Masonry and project specifications: [HBRC code catalog](https://www.hbrc.edu.eg/ar/services/code-egypt).
- Preparation, repair, leveling, waterproofing and joints: [Sika Egypt technical documentation](https://egy.sika.com/en/construction.html). Product-specific sheets control ratios, thicknesses and curing.
- Supply piping: [Wavin PP-R/PP-RCT installation manual](https://mediahub.wavin.com/m/137bdffed37c5680/original/PP-RC-PP-RCT-Pipe-Systems-Installation-Manual.pdf).
- Concealed sanitary frames: [Geberit Duofix installation manual](https://cdn.data.geberit.com/documents/966.027.00.0_03.pdf); use the manual for the actual supplied model.
- Power, distribution and protection: [Schneider Electrical Installation Guide](https://www.se.com/eg/en/work/products/product-launch/electrical-installation-guide/).
- Data and cameras: [Axis product manuals](https://help.axis.com/en-us).
- AC: [Daikin FTXF installation example](https://my.daikin.eu/content/dam/document-library/installation-manuals/ac/split/ftxf-f/FTXF20-42F_Installation%20manual_3PEN748643-9M_English.pdf); model-specific procedures remain controlling.
- Fire systems: [Tyco sprinkler documentation](https://www.tyco-fire.com/products-and-solutions/sprinklers-nozzles-and-accessories), approved fire design and the tested firestop system.
- Thermal/acoustic insulation: [ROCKWOOL installation guides](https://www.rockwool.com/uk/resources-and-tools/product-documentation/installation-guides/).
- Tiles: [SikaCeram-30 example](https://egy.sika.com/en/construction/tiling/sikaceram-30.html); its limits are not generalized to other tile adhesives.
- Stone/counters: [Cosentino technical documentation](https://www.cosentino.com/professional/technical-documentation/).
- Laminate: [Quick-Step installation guides](https://int.quick-step.com/en/laminate/installation).
- Paint: [Jotun Majestic Primer](https://www.jotun.com/eg-ar/products-and-services/products/majestic-primer), with the matching finish datasheet.
- Drywall: [Knauf Egypt download center](https://knauf.com/en-EG/tools/download-center).
- Windows: [REHAU specifier guide](https://window.rehau.com/uk-en/rehau-specifier-guide-download).
- Cabinets: [Blum hinge assembly](https://www.blum.com/us/en/products/hingesystems/clip-top/assembly/).
- Shutters: [Somfy Egypt manuals](https://www.somfy.com.eg/en-eg/assistance/downloads).
- Natural-stone joints: [Soudal guidance](https://www.soudal.co.uk/pro/applications/sealing-natural-stone).

These are reference sources and entry points, not proof that every legacy figure is a current Egyptian code requirement. New text distinguishes project drawings and manufacturer instructions from practical site guidance. Brand examples do not certify local stock availability.

## Corrections and scope

The first compilation exposed two existing references to a missing MODERATE defect-severity enum. The value and its existing badge styling are supplied so those records can compile.

- ECP 203 was incorrectly named as a plaster, flooring, paint and carpentry code. Those references now point to the relevant project/system specification; the concrete reference remains.
- A fixed 15–20 bar / 24-hour plumbing test is replaced with the selected system's approved test procedure and component ratings.
- Cable sections, megger test voltage, RCD trip time, primer dilution and cement-waterproofing mixing are conditional on the actual system.
- Grout joints and movement joints are distinguished; original missing material references are restored or mapped to the corresponding card.
- Engineering design (loads, lintels, anchors, fire performance and cable sizing) requires the approved project details. The guide does not certify an engineering design or claim exhaustive coverage of every proprietary system.

Calculator and report screens, navigation and view-model logic are removed. The unused legacy report table stays in database version 1 to protect existing bookmarks and avoid a destructive migration.

## Verification

Pure catalog tests check coverage, stable IDs, complete phase fields, ordering, actual counts and bidirectional links. Existing repository tests cover lookup and Arabic search. GitHub Actions compiles the debug app and runs unit tests with the existing build configuration. The CI debug key is temporary and cannot update an AI Studio-signed installation.

Visual/device verification is a separate check: confirm the same colors/fonts/cards, Arabic rendering, long-text scrolling, category tabs, search, bookmarks, and every stage/material link on a running Android build. The existing application ID and signing configuration remain unchanged.
