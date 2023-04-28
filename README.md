# elder-scrolls-fandom
This is the repository for the paper [*Cross-Domain Evaluation of POS Taggers: From Wall Street Journal to Fandom Wiki*](https://arxiv.org/abs/2304.13989).

*Elder Scrolls Fandom* (ESF) is an NLP dataset tagged for part-of-speech with the PTB tagset.

The data used in this dataset was obtained from a 2020 database scraping of [elderscrolls.fandom.com](https://elderscrolls.fandom.com/wiki/The_Elder_Scrolls_Wiki), available at [archive.org](https://archive.org/download/wikia_dump_20200214): *elderscrollsfandomcom-20200222-history.xml.7z* in *e.zip*.

The Fandom wikis are licensed under [CC BY-SA](https://creativecommons.org/licenses/by-sa/3.0/).

### Contents

* **esf.conll**: the ESF dataset
* **ptb2conll.java**: a small java program used for converting WSJ from .ptb to .conll
* **predictions**: the predictions made by the taggers for ESF

A guide for downloading and preprocessing Fandom Wikis is available at [robvanderg.github.io](https://robvanderg.github.io/blog/wikia.htm).
