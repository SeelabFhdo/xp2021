#!/usr/bin/env bash
cloc --by-file --force-lang-def="cloc_scripts/cloc_defs/cloc_lemma_def" "../LEMMA_Models_and_GenCode/domain/"
cloc --by-file --force-lang-def="cloc_scripts/cloc_defs/cloc_lemma_def" "../LEMMA_Models_and_GenCode/microservice/"
cloc --by-file --force-lang-def="cloc_scripts/cloc_defs/cloc_lemma_def" "../LEMMA_Models_and_GenCode/operation/"
cloc --by-file  "../LEMMA_Models_and_GenCode/generated_code/"