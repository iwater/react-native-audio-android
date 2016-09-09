var { NativeModules } = require('react-native');
var RNAudioRecord = NativeModules.RNAudioRecord;

var _file = '';

export function startRecording(filename, callback) {
  _file = filename;
  callback = callback || (err => console.log(['startRecording', filename, err]))
  RNAudioRecord.startRecording(filename, callback)
}

export function stopRecording(callback) {
  callback = callback || (err => {
    console.log(['stopRecording', err])
    startPlaying(_file)
  })

  RNAudioRecord.stopRecording(callback)
}

export function startPlaying(filename, callback) {
  callback = callback || (err => console.log(['startPlaying', filename, err]))
  RNAudioRecord.startPlaying(filename, callback)
}

export function stopPlaying(callback) {
  callback = callback || (err => console.log(['stopPlaying', err]))
  RNAudioRecord.stopPlaying(callback)
}

export function getDuration(filename, cb) {
  RNAudioRecord.getDuration(filename, cb)
}