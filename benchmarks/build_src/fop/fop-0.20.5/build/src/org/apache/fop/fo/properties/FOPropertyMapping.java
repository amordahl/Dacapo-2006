
package org.apache.fop.fo.properties;

import java.util.HashMap;
import java.util.Set;
//import org.apache.fop.svg.*;

public class FOPropertyMapping {

  private static HashMap s_htGeneric = new HashMap();
  private static HashMap s_htElementLists = new HashMap();
  
  static {
    // Generate the generic mapping

  /* PROPCLASS = ColorTypeProperty */

  /* PROPCLASS = EnumProperty */

  /* PROPCLASS = KeepProperty */

  /* PROPCLASS = CondLengthProperty */

  /* PROPCLASS = CondLengthProperty */

  /* PROPCLASS = LengthProperty */

  /* PROPCLASS = CondLengthProperty */

  /* PROPCLASS = LengthProperty */

  /* PROPCLASS = EnumProperty */

  /* PROPCLASS = EnumProperty */

  /* PROPCLASS = SpaceProperty */
    s_htGeneric.put("source-document", SourceDocumentMaker.maker("source-document"));
    s_htGeneric.put("role", RoleMaker.maker("role"));
    s_htGeneric.put("absolute-position", AbsolutePositionMaker.maker("absolute-position"));
    s_htGeneric.put("top", TopMaker.maker("top"));
    s_htGeneric.put("right", RightMaker.maker("right"));
    s_htGeneric.put("bottom", BottomMaker.maker("bottom"));
    s_htGeneric.put("left", LeftMaker.maker("left"));
    s_htGeneric.put("azimuth", AzimuthMaker.maker("azimuth"));
    s_htGeneric.put("cue-after", CueAfterMaker.maker("cue-after"));
    s_htGeneric.put("cue-before", CueBeforeMaker.maker("cue-before"));
    s_htGeneric.put("elevation", ElevationMaker.maker("elevation"));
    s_htGeneric.put("pause-after", PauseAfterMaker.maker("pause-after"));
    s_htGeneric.put("pause-before", PauseBeforeMaker.maker("pause-before"));
    s_htGeneric.put("pitch", PitchMaker.maker("pitch"));
    s_htGeneric.put("pitch-range", PitchRangeMaker.maker("pitch-range"));
    s_htGeneric.put("play-during", PlayDuringMaker.maker("play-during"));
    s_htGeneric.put("richness", RichnessMaker.maker("richness"));
    s_htGeneric.put("speak", SpeakMaker.maker("speak"));
    s_htGeneric.put("speak-header", SpeakHeaderMaker.maker("speak-header"));
    s_htGeneric.put("speak-numeral", SpeakNumeralMaker.maker("speak-numeral"));
    s_htGeneric.put("speak-punctuation", SpeakPunctuationMaker.maker("speak-punctuation"));
    s_htGeneric.put("speech-rate", SpeechRateMaker.maker("speech-rate"));
    s_htGeneric.put("stress", StressMaker.maker("stress"));
    s_htGeneric.put("voice-family", VoiceFamilyMaker.maker("voice-family"));
    s_htGeneric.put("volume", VolumeMaker.maker("volume"));
    s_htGeneric.put("background-attachment", BackgroundAttachmentMaker.maker("background-attachment"));
    s_htGeneric.put("background-color", BackgroundColorMaker.maker("background-color"));
    s_htGeneric.put("background-image", BackgroundImageMaker.maker("background-image"));
    s_htGeneric.put("background-repeat", BackgroundRepeatMaker.maker("background-repeat"));
    s_htGeneric.put("background-position-horizontal", BackgroundPositionHorizontalMaker.maker("background-position-horizontal"));
    s_htGeneric.put("background-position-vertical", BackgroundPositionVerticalMaker.maker("background-position-vertical"));
    s_htGeneric.put("border-before-color", BorderBeforeColorMaker.maker("border-before-color"));
    s_htGeneric.put("border-before-style", BorderBeforeStyleMaker.maker("border-before-style"));
    s_htGeneric.put("border-before-width", BorderBeforeWidthMaker.maker("border-before-width"));
    s_htGeneric.put("border-after-color", BorderAfterColorMaker.maker("border-after-color"));
    s_htGeneric.put("border-after-style", BorderAfterStyleMaker.maker("border-after-style"));
    s_htGeneric.put("border-after-width", BorderAfterWidthMaker.maker("border-after-width"));
    s_htGeneric.put("border-start-color", BorderStartColorMaker.maker("border-start-color"));
    s_htGeneric.put("border-start-style", BorderStartStyleMaker.maker("border-start-style"));
    s_htGeneric.put("border-start-width", BorderStartWidthMaker.maker("border-start-width"));
    s_htGeneric.put("border-end-color", BorderEndColorMaker.maker("border-end-color"));
    s_htGeneric.put("border-end-style", BorderEndStyleMaker.maker("border-end-style"));
    s_htGeneric.put("border-end-width", BorderEndWidthMaker.maker("border-end-width"));
    s_htGeneric.put("border-top-color", BorderTopColorMaker.maker("border-top-color"));
    s_htGeneric.put("border-top-style", BorderTopStyleMaker.maker("border-top-style"));
    s_htGeneric.put("border-top-width", BorderTopWidthMaker.maker("border-top-width"));
    s_htGeneric.put("border-bottom-color", BorderBottomColorMaker.maker("border-bottom-color"));
    s_htGeneric.put("border-bottom-style", BorderBottomStyleMaker.maker("border-bottom-style"));
    s_htGeneric.put("border-bottom-width", BorderBottomWidthMaker.maker("border-bottom-width"));
    s_htGeneric.put("border-left-color", BorderLeftColorMaker.maker("border-left-color"));
    s_htGeneric.put("border-left-style", BorderLeftStyleMaker.maker("border-left-style"));
    s_htGeneric.put("border-left-width", BorderLeftWidthMaker.maker("border-left-width"));
    s_htGeneric.put("border-right-color", BorderRightColorMaker.maker("border-right-color"));
    s_htGeneric.put("border-right-style", BorderRightStyleMaker.maker("border-right-style"));
    s_htGeneric.put("border-right-width", BorderRightWidthMaker.maker("border-right-width"));
    s_htGeneric.put("padding-before", PaddingBeforeMaker.maker("padding-before"));
    s_htGeneric.put("padding-after", PaddingAfterMaker.maker("padding-after"));
    s_htGeneric.put("padding-start", PaddingStartMaker.maker("padding-start"));
    s_htGeneric.put("padding-end", PaddingEndMaker.maker("padding-end"));
    s_htGeneric.put("padding-top", PaddingTopMaker.maker("padding-top"));
    s_htGeneric.put("padding-bottom", PaddingBottomMaker.maker("padding-bottom"));
    s_htGeneric.put("padding-left", PaddingLeftMaker.maker("padding-left"));
    s_htGeneric.put("padding-right", PaddingRightMaker.maker("padding-right"));
    s_htGeneric.put("font-family", FontFamilyMaker.maker("font-family"));
    s_htGeneric.put("font-selection-strategy", FontSelectionStrategyMaker.maker("font-selection-strategy"));
    s_htGeneric.put("font-size", FontSizeMaker.maker("font-size"));
    s_htGeneric.put("font-stretch", FontStretchMaker.maker("font-stretch"));
    s_htGeneric.put("font-size-adjust", FontSizeAdjustMaker.maker("font-size-adjust"));
    s_htGeneric.put("font-style", FontStyleMaker.maker("font-style"));
    s_htGeneric.put("font-variant", FontVariantMaker.maker("font-variant"));
    s_htGeneric.put("font-weight", FontWeightMaker.maker("font-weight"));
    s_htGeneric.put("country", CountryMaker.maker("country"));
    s_htGeneric.put("language", LanguageMaker.maker("language"));
    s_htGeneric.put("script", ScriptMaker.maker("script"));
    s_htGeneric.put("hyphenate", HyphenateMaker.maker("hyphenate"));
    s_htGeneric.put("hyphenation-character", HyphenationCharacterMaker.maker("hyphenation-character"));
    s_htGeneric.put("hyphenation-push-character-count", HyphenationPushCharacterCountMaker.maker("hyphenation-push-character-count"));
    s_htGeneric.put("hyphenation-remain-character-count", HyphenationRemainCharacterCountMaker.maker("hyphenation-remain-character-count"));
    s_htGeneric.put("margin-top", MarginTopMaker.maker("margin-top"));
    s_htGeneric.put("margin-bottom", MarginBottomMaker.maker("margin-bottom"));
    s_htGeneric.put("margin-left", MarginLeftMaker.maker("margin-left"));
    s_htGeneric.put("margin-right", MarginRightMaker.maker("margin-right"));
    s_htGeneric.put("space-before", GenericSpace.maker("space-before"));
    s_htGeneric.put("space-after", GenericSpace.maker("space-after"));
    s_htGeneric.put("start-indent", StartIndentMaker.maker("start-indent"));
    s_htGeneric.put("end-indent", EndIndentMaker.maker("end-indent"));
    s_htGeneric.put("space-end", GenericSpace.maker("space-end"));
    s_htGeneric.put("space-start", GenericSpace.maker("space-start"));
    s_htGeneric.put("relative-position", RelativePositionMaker.maker("relative-position"));
    s_htGeneric.put("alignment-adjust", AlignmentAdjustMaker.maker("alignment-adjust"));
    s_htGeneric.put("alignment-baseline", AlignmentBaselineMaker.maker("alignment-baseline"));
    s_htGeneric.put("baseline-shift", BaselineShiftMaker.maker("baseline-shift"));
    s_htGeneric.put("display-align", DisplayAlignMaker.maker("display-align"));
    s_htGeneric.put("dominant-baseline", DominantBaselineMaker.maker("dominant-baseline"));
    s_htGeneric.put("relative-align", RelativeAlignMaker.maker("relative-align"));
    s_htGeneric.put("block-progression-dimension", BlockProgressionDimensionMaker.maker("block-progression-dimension"));
    s_htGeneric.put("content-height", ContentHeightMaker.maker("content-height"));
    s_htGeneric.put("content-width", ContentWidthMaker.maker("content-width"));
    s_htGeneric.put("height", HeightMaker.maker("height"));
    s_htGeneric.put("inline-progression-dimension", InlineProgressionDimensionMaker.maker("inline-progression-dimension"));
    s_htGeneric.put("max-height", MaxHeightMaker.maker("max-height"));
    s_htGeneric.put("max-width", MaxWidthMaker.maker("max-width"));
    s_htGeneric.put("min-height", MinHeightMaker.maker("min-height"));
    s_htGeneric.put("min-width", MinWidthMaker.maker("min-width"));
    s_htGeneric.put("scaling", ScalingMaker.maker("scaling"));
    s_htGeneric.put("scaling-method", ScalingMethodMaker.maker("scaling-method"));
    s_htGeneric.put("width", WidthMaker.maker("width"));
    s_htGeneric.put("hyphenation-keep", HyphenationKeepMaker.maker("hyphenation-keep"));
    s_htGeneric.put("hyphenation-ladder-count", HyphenationLadderCountMaker.maker("hyphenation-ladder-count"));
    s_htGeneric.put("last-line-end-indent", LastLineEndIndentMaker.maker("last-line-end-indent"));
    s_htGeneric.put("line-height", LineHeightMaker.maker("line-height"));
    s_htGeneric.put("line-height-shift-adjustment", LineHeightShiftAdjustmentMaker.maker("line-height-shift-adjustment"));
    s_htGeneric.put("line-stacking-strategy", LineStackingStrategyMaker.maker("line-stacking-strategy"));
    s_htGeneric.put("linefeed-treatment", LinefeedTreatmentMaker.maker("linefeed-treatment"));
    s_htGeneric.put("white-space-treatment", WhiteSpaceTreatmentMaker.maker("white-space-treatment"));
    s_htGeneric.put("text-align", TextAlignMaker.maker("text-align"));
    s_htGeneric.put("text-align-last", TextAlignLastMaker.maker("text-align-last"));
    s_htGeneric.put("text-indent", TextIndentMaker.maker("text-indent"));
    s_htGeneric.put("white-space-collapse", WhiteSpaceCollapseMaker.maker("white-space-collapse"));
    s_htGeneric.put("wrap-option", WrapOptionMaker.maker("wrap-option"));
    s_htGeneric.put("character", CharacterMaker.maker("character"));
    s_htGeneric.put("letter-spacing", LetterSpacingMaker.maker("letter-spacing"));
    s_htGeneric.put("suppress-at-line-break", SuppressAtLineBreakMaker.maker("suppress-at-line-break"));
    s_htGeneric.put("text-decoration", TextDecorationMaker.maker("text-decoration"));
    s_htGeneric.put("text-shadow", TextShadowMaker.maker("text-shadow"));
    s_htGeneric.put("text-transform", TextTransformMaker.maker("text-transform"));
    s_htGeneric.put("treat-as-word-space", TreatAsWordSpaceMaker.maker("treat-as-word-space"));
    s_htGeneric.put("word-spacing", WordSpacingMaker.maker("word-spacing"));
    s_htGeneric.put("color", ColorMaker.maker("color"));
    s_htGeneric.put("color-profile-name", ColorProfileNameMaker.maker("color-profile-name"));
    s_htGeneric.put("rendering-intent", RenderingIntentMaker.maker("rendering-intent"));
    s_htGeneric.put("clear", ClearMaker.maker("clear"));
    s_htGeneric.put("float", FloatMaker.maker("float"));
    s_htGeneric.put("break-after", GenericBreak.maker("break-after"));
    s_htGeneric.put("break-before", GenericBreak.maker("break-before"));
    s_htGeneric.put("keep-together", KeepTogetherMaker.maker("keep-together"));
    s_htGeneric.put("keep-with-next", KeepWithNextMaker.maker("keep-with-next"));
    s_htGeneric.put("keep-with-previous", KeepWithPreviousMaker.maker("keep-with-previous"));
    s_htGeneric.put("orphans", OrphansMaker.maker("orphans"));
    s_htGeneric.put("widows", WidowsMaker.maker("widows"));
    s_htGeneric.put("clip", ClipMaker.maker("clip"));
    s_htGeneric.put("overflow", OverflowMaker.maker("overflow"));
    s_htGeneric.put("reference-orientation", ReferenceOrientationMaker.maker("reference-orientation"));
    s_htGeneric.put("span", SpanMaker.maker("span"));
    s_htGeneric.put("leader-alignment", LeaderAlignmentMaker.maker("leader-alignment"));
    s_htGeneric.put("leader-pattern", LeaderPatternMaker.maker("leader-pattern"));
    s_htGeneric.put("leader-pattern-width", LeaderPatternWidthMaker.maker("leader-pattern-width"));
    s_htGeneric.put("leader-length", LeaderLengthMaker.maker("leader-length"));
    s_htGeneric.put("rule-style", RuleStyleMaker.maker("rule-style"));
    s_htGeneric.put("rule-thickness", RuleThicknessMaker.maker("rule-thickness"));
    s_htGeneric.put("active-state", ActiveStateMaker.maker("active-state"));
    s_htGeneric.put("auto-restore", AutoRestoreMaker.maker("auto-restore"));
    s_htGeneric.put("case-name", CaseNameMaker.maker("case-name"));
    s_htGeneric.put("case-title", CaseTitleMaker.maker("case-title"));
    s_htGeneric.put("destination-placement-offset", DestinationPlacementOffsetMaker.maker("destination-placement-offset"));
    s_htGeneric.put("external-destination", ExternalDestinationMaker.maker("external-destination"));
    s_htGeneric.put("indicate-destination", IndicateDestinationMaker.maker("indicate-destination"));
    s_htGeneric.put("internal-destination", InternalDestinationMaker.maker("internal-destination"));
    s_htGeneric.put("destination-name", DestinationNameMaker.maker("destination-name"));
    s_htGeneric.put("show-destination", ShowDestinationMaker.maker("show-destination"));
    s_htGeneric.put("starting-state", StartingStateMaker.maker("starting-state"));
    s_htGeneric.put("switch-to", SwitchToMaker.maker("switch-to"));
    s_htGeneric.put("target-presentation-context", TargetPresentationContextMaker.maker("target-presentation-context"));
    s_htGeneric.put("target-processing-context", TargetProcessingContextMaker.maker("target-processing-context"));
    s_htGeneric.put("target-stylesheet", TargetStylesheetMaker.maker("target-stylesheet"));
    s_htGeneric.put("marker-class-name", MarkerClassNameMaker.maker("marker-class-name"));
    s_htGeneric.put("retrieve-class-name", RetrieveClassNameMaker.maker("retrieve-class-name"));
    s_htGeneric.put("retrieve-position", RetrievePositionMaker.maker("retrieve-position"));
    s_htGeneric.put("retrieve-boundary", RetrieveBoundaryMaker.maker("retrieve-boundary"));
    s_htGeneric.put("format", FormatMaker.maker("format"));
    s_htGeneric.put("grouping-separator", GroupingSeparatorMaker.maker("grouping-separator"));
    s_htGeneric.put("grouping-size", GroupingSizeMaker.maker("grouping-size"));
    s_htGeneric.put("letter-value", LetterValueMaker.maker("letter-value"));
    s_htGeneric.put("blank-or-not-blank", BlankOrNotBlankMaker.maker("blank-or-not-blank"));
    s_htGeneric.put("column-count", ColumnCountMaker.maker("column-count"));
    s_htGeneric.put("column-gap", ColumnGapMaker.maker("column-gap"));
    s_htGeneric.put("extent", ExtentMaker.maker("extent"));
    s_htGeneric.put("flow-name", FlowNameMaker.maker("flow-name"));
    s_htGeneric.put("force-page-count", ForcePageCountMaker.maker("force-page-count"));
    s_htGeneric.put("initial-page-number", InitialPageNumberMaker.maker("initial-page-number"));
    s_htGeneric.put("master-name", MasterNameMaker.maker("master-name"));
    s_htGeneric.put("master-reference", MasterReferenceMaker.maker("master-reference"));
    s_htGeneric.put("maximum-repeats", MaximumRepeatsMaker.maker("maximum-repeats"));
    s_htGeneric.put("media-usage", MediaUsageMaker.maker("media-usage"));
    s_htGeneric.put("odd-or-even", OddOrEvenMaker.maker("odd-or-even"));
    s_htGeneric.put("page-height", PageHeightMaker.maker("page-height"));
    s_htGeneric.put("page-position", PagePositionMaker.maker("page-position"));
    s_htGeneric.put("page-width", PageWidthMaker.maker("page-width"));
    s_htGeneric.put("precedence", PrecedenceMaker.maker("precedence"));
    s_htGeneric.put("region-name", RegionNameMaker.maker("region-name"));
    s_htGeneric.put("border-after-precedence", BorderAfterPrecedenceMaker.maker("border-after-precedence"));
    s_htGeneric.put("border-before-precedence", BorderBeforePrecedenceMaker.maker("border-before-precedence"));
    s_htGeneric.put("border-collapse", BorderCollapseMaker.maker("border-collapse"));
    s_htGeneric.put("border-end-precedence", BorderEndPrecedenceMaker.maker("border-end-precedence"));
    s_htGeneric.put("border-separation", BorderSeparationMaker.maker("border-separation"));
    s_htGeneric.put("border-start-precedence", BorderStartPrecedenceMaker.maker("border-start-precedence"));
    s_htGeneric.put("caption-side", CaptionSideMaker.maker("caption-side"));
    s_htGeneric.put("column-number", ColumnNumberMaker.maker("column-number"));
    s_htGeneric.put("column-width", ColumnWidthMaker.maker("column-width"));
    s_htGeneric.put("empty-cells", EmptyCellsMaker.maker("empty-cells"));
    s_htGeneric.put("ends-row", EndsRowMaker.maker("ends-row"));
    s_htGeneric.put("number-columns-repeated", NumberColumnsRepeatedMaker.maker("number-columns-repeated"));
    s_htGeneric.put("number-columns-spanned", NumberColumnsSpannedMaker.maker("number-columns-spanned"));
    s_htGeneric.put("number-rows-spanned", NumberRowsSpannedMaker.maker("number-rows-spanned"));
    s_htGeneric.put("starts-row", StartsRowMaker.maker("starts-row"));
    s_htGeneric.put("table-layout", TableLayoutMaker.maker("table-layout"));
    s_htGeneric.put("table-omit-footer-at-break", TableOmitFooterAtBreakMaker.maker("table-omit-footer-at-break"));
    s_htGeneric.put("table-omit-header-at-break", TableOmitHeaderAtBreakMaker.maker("table-omit-header-at-break"));
    s_htGeneric.put("direction", DirectionMaker.maker("direction"));
    s_htGeneric.put("glyph-orientation-horizontal", GlyphOrientationHorizontalMaker.maker("glyph-orientation-horizontal"));
    s_htGeneric.put("glyph-orientation-vertical", GlyphOrientationVerticalMaker.maker("glyph-orientation-vertical"));
    s_htGeneric.put("text-altitude", TextAltitudeMaker.maker("text-altitude"));
    s_htGeneric.put("text-depth", TextDepthMaker.maker("text-depth"));
    s_htGeneric.put("unicode-bidi", UnicodeBidiMaker.maker("unicode-bidi"));
    s_htGeneric.put("writing-mode", WritingModeMaker.maker("writing-mode"));
    s_htGeneric.put("content-type", ContentTypeMaker.maker("content-type"));
    s_htGeneric.put("id", IdMaker.maker("id"));
    s_htGeneric.put("provisional-label-separation", ProvisionalLabelSeparationMaker.maker("provisional-label-separation"));
    s_htGeneric.put("provisional-distance-between-starts", ProvisionalDistanceBetweenStartsMaker.maker("provisional-distance-between-starts"));
    s_htGeneric.put("ref-id", RefIdMaker.maker("ref-id"));
    s_htGeneric.put("score-spaces", ScoreSpacesMaker.maker("score-spaces"));
    s_htGeneric.put("src", SrcMaker.maker("src"));
    s_htGeneric.put("visibility", VisibilityMaker.maker("visibility"));
    s_htGeneric.put("z-index", ZIndexMaker.maker("z-index"));
    s_htGeneric.put("background", BackgroundMaker.maker("background"));
    s_htGeneric.put("background-position", BackgroundPositionMaker.maker("background-position"));
    s_htGeneric.put("border", BorderMaker.maker("border"));
    s_htGeneric.put("border-bottom", BorderBottomMaker.maker("border-bottom"));
    s_htGeneric.put("border-color", BorderColorMaker.maker("border-color"));
    s_htGeneric.put("border-left", BorderLeftMaker.maker("border-left"));
    s_htGeneric.put("border-right", BorderRightMaker.maker("border-right"));
    s_htGeneric.put("border-style", BorderStyleMaker.maker("border-style"));
    s_htGeneric.put("border-spacing", BorderSpacingMaker.maker("border-spacing"));
    s_htGeneric.put("border-top", BorderTopMaker.maker("border-top"));
    s_htGeneric.put("border-width", BorderWidthMaker.maker("border-width"));
    s_htGeneric.put("cue", CueMaker.maker("cue"));
    s_htGeneric.put("font", FontMaker.maker("font"));
    s_htGeneric.put("margin", MarginMaker.maker("margin"));
    s_htGeneric.put("padding", PaddingMaker.maker("padding"));
    s_htGeneric.put("page-break-after", PageBreakAfterMaker.maker("page-break-after"));
    s_htGeneric.put("page-break-before", PageBreakBeforeMaker.maker("page-break-before"));
    s_htGeneric.put("page-break-inside", PageBreakInsideMaker.maker("page-break-inside"));
    s_htGeneric.put("pause", PauseMaker.maker("pause"));
    s_htGeneric.put("position", PositionMaker.maker("position"));
    s_htGeneric.put("size", SizeMaker.maker("size"));
    s_htGeneric.put("vertical-align", VerticalAlignMaker.maker("vertical-align"));
    s_htGeneric.put("white-space", WhiteSpaceMaker.maker("white-space"));
    s_htGeneric.put("xml:lang", XMLLangMaker.maker("xml:lang"));

  }


  public static HashMap getGenericMappings() {
    return s_htGeneric;
  }

  public static Set getElementMappings() {
    return s_htElementLists.keySet();
  }

  public static HashMap getElementMapping(String elemName) {
    return (HashMap)s_htElementLists.get(elemName);
  }
}
