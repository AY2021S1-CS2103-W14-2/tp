package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BOOKING_ID;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.booking.Booking;

/**
 * Deletes a booking identified using it's displayed index from the address book.
 */
public class DeleteBookingCommand extends Command {

    public static final String COMMAND_WORD = "deleteBooking";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the booking identified by bookingId.\n"
            + "Parameters: "
            + PREFIX_BOOKING_ID + "BOOKING_ID (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + PREFIX_BOOKING_ID + " 1";

    public static final String MESSAGE_DELETE_BOOKING_SUCCESS = "Deleted Booking: %1$s";

    private final Integer bookingId;

    public DeleteBookingCommand(Integer bookingId) {
        this.bookingId = bookingId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        assert bookingId > 0;
        if (!model.hasBookingWithId(bookingId)) {
            throw new CommandException(Messages.MESSAGE_INVALID_BOOKING_DISPLAYED_ID);
        }

        Booking bookingToDelete = model.getBookingWithId(bookingId);
        model.deleteBooking(bookingToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_BOOKING_SUCCESS, bookingToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteBookingCommand // instanceof handles nulls
                && bookingId.equals(((DeleteBookingCommand) other).bookingId)); // state check
    }
}