# CHANGELOG

## 1.2.2

- Every rule can have associated error messages.

## 1.2.1

- Improve documentation.
- Fix typo in alphabetical sequence.
- Use the not minified version of the library in bower as main file.

## 1.2.0

- Listen also to the `change` and `onpaste` events, not only to the `onkeyup`.
- Show the lowest verdict when the score is below zero.
- New option to pass more input fields content to the zxcvbn library.
- Don't show the verdicts inside the popover if they are being showed inside
  the progressbar.

## 1.1.5

- Better Bower configuration.
- Pass also the verdict level to the "on key up" event handler.
- Add a basic usage section to the readme.

## 1.1.4

- Bower support.

## 1.1.3

- Pass the score and the verdict to the "on key up" event handler.

## 1.1.2

- Upgrade dev dependencies: grunt plugins and jquery
- Bugfix in sequences lookup
- New tests for sequences lookup

## 1.1.1

- Pass the username field content to the zxcvbn function, so zxcvbn takes it
  into consideration when scoring the password.
- Add a debug option, so the score gets printed in the JS console.
- Check reversed sequences too in the sequences rule.
- Fix the popover flickering.

## 1.1.0

- Support zxcvbn for password scoring.
- Support showing the password strength as a validation status in the password
  field.
- Support hiding the progress bar, making it optional.
- Support showing the verdicts inside the progress bar.

## 1.0.2

- Bugfix in UI initialization.
- Fix typo in readme.

## 1.0.1

- Separate source file in several smaller files.
- Add Grunt support for creating a bundle and a minified version.
- Add tests for the rules engine, and continuos integration with Travis.

## 1.0.0

- Complete refactor of the code. This is a cleaner version, easier to extend
  and mantain.
- Broke backwards compatibility. Bootstrap 3 is the default option now, other
  options default values have changed. Options structure has changed too.
- Old tests have been renamed to examples, which is what they really are. Leave
  room for real tests.

## 0.7.0

- New rule to check for sequences in the password. It penalizes finding
  sequences of consecutive numbers, consecutive characters in the alphabet or
  in the qwerty layout. Active by default.

## 0.6.0

- New feature: support showing the verdicts and errors in a Bootstrap popover.
- Hide the verdicts and errors when the input is empty.
- Remove _showVerdictsInitially_ option, is not needed anymore.

## 0.5.0

- Support to activate/deactivate rules using the _rules_ object inside the
  _options_ object.
- Two new rules added, deactivated by default. Check for too many character
  repetitions, and check for number of character classes used.

## 0.4.5

- Fix error message when the password contains the username.
- Check if the password is an email, and mark as weak.
- Add a _container_ option, it will be used to look for the viewports.

## 0.4.4

- Bad version in plugin manifest.

## 0.4.3

- Change jQuery plugin name to avoid conflict with an existing one.

## 0.4.2

- New option to choose if the verdicts should be displayed before the user
  introduces a letter. New default behaviour: don't show them.
- Bugfix with progress bar color and Bootstrap 2.
- Improve code quality.

## 0.4.1

- jQuery plugins registry support.

## 0.4.0

- Bootstrap 3.0.0 support.
