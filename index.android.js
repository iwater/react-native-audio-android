var { NativeModules } = require('react-native');
var RNAudioRecord = NativeModules.RNAudioRecord;

var _file = '';

export function startRecording(filename) {
  _file = filename;
  RNAudioRecord.startRecording(filename, err => console.log(['startRecording', filename, err]))
}
  
export function stopRecording() {
  RNAudioRecord.stopRecording(err => {
    console.log(['stopRecording', err])
    startPlaying(_file)
  })
}

export function startPlaying(filename) {
  RNAudioRecord.startPlaying(filename, err => console.log(['startPlaying', filename, err]))
}
  
export function stopPlaying() {
  RNAudioRecord.stopPlaying(err => console.log(['stopPlaying', err]))
}