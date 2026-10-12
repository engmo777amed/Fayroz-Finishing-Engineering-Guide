# Finishing guide 1.4 preview

The preview adds realistic 3D educational cutaways and user-controlled text to the reviewed finishing catalog. Existing category, stage and material IDs remain stable.

## Content

- 30 categories, 203 stages and 243 active material cards remain available.
- 125 practical guide entries cover material selection, substrate compatibility, inspection/stop conditions, site terminology and five worked quantity/calculation examples.
- 19 additional defect cases bring the diagnosis/retest library to 34 cases, covering every category.
- Planning and approval stages precede dependent execution where applicable, while previously approved category-specific ordering is retained.
- A critical defect calls for stopping and specialist assessment; its severity label does not prescribe demolition without diagnosis.

## Visuals and dimensions

14 bundled JPEG cutaways share a realistic material-based style. They open offline, enlarge with pinch/pan, and carry editable explanation and dimension text on the image.

The generated render is an educational concept, not a measured or approved shop drawing. It cannot establish fixing loads, wiring connections, reinforcement, fire rating, acoustic performance, service routes or quantities. Every image explains its scope; product-specific numbers are not generalized to other products.

Verified numeric annotations:

| Figure | Annotation | Primary source |
|---|---|---|
| Tiled wet floor | SikaCeram-30 final solid adhesive bed 3–6 mm | https://egy.sika.com/en/construction/tiling/sikaceram-30.html |
| Gypsum partition | Example: Knauf Regular board 12.5 mm | https://knauf.com/en-EG/p/product/knauf-regular-gypsum-board-12.5-mm-10003_0639 |
| Floating laminate | Quick-Step laminate perimeter movement gap 8–10 mm | https://www.quick-step.co.uk/en-gb/frequently-asked-questions/laminate/preparation-and-installation/what-is-the-maximum-floor-length-i-can-install-without-expansion-gap |

Other images identify the dimensions that must come from the selected system, model or project design. Values are supplied as native editable text, avoiding baked-in Arabic or fictitious dimensions in generated artwork. Floor slope and material quantities are worked examples with stated assumptions, not design requirements.

## Editing

Long press displayed text, or use the pencil button and searchable field editor. Catalog fields use stable record/field keys in separate SharedPreferences. Lists preserve their existing indices. Overrides do not change IDs, links, image filenames or structural metadata. A saved field updates repository views and search; clearing that override restores the current bundled original. Ordinary app upgrades retain application data; uninstalling or clearing storage removes local edits.

No production signing settings were changed. The separately installable preview uses the existing preview application ID and cached signing key, with versionCode 4 and versionName 1.4-preview.

Validation covers persistence after store recreation, simulated bundled-text changes, reset/blank validation, repository/search updates, long-press save/reset, category coverage, readable bundled image assets and existing catalog/navigation regressions. A physical Android device remains to be tested.
