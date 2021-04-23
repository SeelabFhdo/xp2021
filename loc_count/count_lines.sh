#!/usr/bin/env bash
cloc --by-file --force-lang-def="cloc_scripts/cloc_defs/cloc_lemma_def" "../puls_lemma_models/BookingService/"
cloc --by-file  "../generated_code/"